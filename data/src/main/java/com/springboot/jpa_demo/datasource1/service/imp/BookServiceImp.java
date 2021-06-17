package com.springboot.jpa_demo.datasource1.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Book;
import com.springboot.jpa_demo.datasource1.repository.BookRepository;
import com.springboot.jpa_demo.datasource1.service.BookService;
import com.springboot.jpa_demo.utils.ConstantVar;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getallGym() {
        System.out.println("从数据库拿取数据");
        return bookRepository.findAll();
    }

    @KafkaListener(topics = "updateGym", groupId = "group_id")
    public void consumeGymUpdate(Book book) {
        JSONObject res = new JSONObject();
        int id = book.getId();
        Book valid = bookRepository.findById(id);
        if (valid != null) {
            bookRepository.save(book);
            res.put("code", 200);
        } else {
            res.put("code", 204);
            res.put("message", "该gym不存在");
        }
        return res;
    }

    @Override
    public JSONObject update(Book book) {
        JSONObject res = new JSONObject();
        int id = book.getId();
        Book valid = bookRepository.findById(id);
        if (valid != null) {
            bookRepository.save(book);
            res.put("code", 200);
        } else {
            res.put("code", 204);
            res.put("message", "该gym不存在");
        }
        return res;
    }

    @Override
    public JSONObject findByIdOrName(String id, String name) {
        JSONObject res = new JSONObject();
        res.put("data", bookRepository.findByIdOrName(id, name));
        return res;
    }

    @Override
    public JSONObject findByNameLike(String name) {
        JSONObject res = new JSONObject();
        res.put("data", bookRepository.findByNameLike("%" + name + "%"));
        return res;
    }

    @Override
    public JSONObject findByNameContaining(String name) {
        JSONObject res = new JSONObject();
        res.put("data", bookRepository.findByNameContaining(name));
        return res;
    }

    @Override
    public JSONObject findByNameIn(Collection name) {
        JSONObject res = new JSONObject();
        res.put("data", bookRepository.findByNameIn(name));
        return res;
    }

    @Override
    public JSONObject findAll(Pageable pageable) {
        JSONObject res = new JSONObject();
        res.put("data", bookRepository.findAll(pageable));
        return res;
    }

    /**
     *
     * @param id 体育馆的id
     * @return 根据gym的ID联表查询该gym的所有trainer
     */
    @Override
    public JSONObject getGymTrainer(String id) {
        JSONObject res = new JSONObject();
        res.put("data", bookRepository.getGymTrainer(id));
        return res;
    }

    @Override
    public JSONObject addGym(Book book) {
        JSONObject res = new JSONObject();
        Book book1 = bookRepository.save(book);

        res.put("data", book1);
        res.put("code", ConstantVar.SUCCESSFUL_CODE);
        res.put("message", ConstantVar.SUCCESSFUL_MESSAGE);
        return res;

    }
}
