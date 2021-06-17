package com.springboot.jpa_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Book;
import com.springboot.jpa_demo.datasource1.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.TimeUnit;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
@CacheConfig(cacheNames = { "BookCache" })
public class BookController {

    @Autowired
    BookService bookService;

    @ApiOperation(value = "获取图书馆的所有信息", notes = "从第一个数据源获取图书馆的所有信息")
    @GetMapping("api/v1/gym/all")
    @Cacheable(key = "targetClass + methodName")
    public ResponseEntity<JSONObject> getAllGym() {
        JSONObject res = new JSONObject();
        res.put("data", bookService.getallGym());
        // 额外增加CacheControl头部，开启强缓存
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(86400, TimeUnit.SECONDS)).body(res);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("api/v1/gym/update")
    @CachePut(key = "targetClass + methodName")
    public JSONObject updateGym(@RequestBody Book book) {
        // 使用 Kafka 改写部分
        this.kafkaTemplate.send("updateGym", message);
        return bookService.update(book);
    }

    @PostMapping("api/v1/gym/findByNameLike/{name}")
    public JSONObject findByNameLike(@PathVariable String name) {
        return bookService.findByNameLike(name);
    }

    @PostMapping("api/v1/gym/findByNameContaining/{name}")
    public JSONObject findByNameContaining(@PathVariable String name) {
        return bookService.findByNameContaining(name);
    }

    @PostMapping("api/v1/gym/{pageSize}/{pageNum}")
    public JSONObject findAll(@PathVariable Integer pageSize, @PathVariable Integer pageNum) {
        JSONObject result = new JSONObject();
        try {
            System.out.println("pageSize: " + pageSize);
            System.out.println("pageNum: " + pageNum);
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            Pageable pageable = new PageRequest(pageNum, pageSize, sort);
            result = bookService.findAll(pageable);

        } catch (Exception e) {
            result.put("code", 500);
            return result;
        }
        result.put("code", 200);
        return result;
    }

    // @PostMapping("/gym/trainer/{id}")
    // public JSONObject getGymTrainerById(@PathVariable String id) {
    // return gymService.getGymTrainer(id);
    // }

}
