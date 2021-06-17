package com.springboot.jpa_demo.datasource1.service;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Book;
import org.hibernate.mapping.Collection;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getallGym();

    JSONObject update(Book book);

    JSONObject findByIdOrName(String id,String name);

    JSONObject findByNameLike(String name);

    JSONObject findByNameContaining(String name);

    JSONObject findByNameIn(Collection name);

    JSONObject findAll(Pageable pageable);

    JSONObject getGymTrainer(String id);

    JSONObject addGym(Book book);
}
