package com.springboot.jpa_demo.controller;

import com.springboot.jpa_demo.datasource1.domain.Book;
import com.springboot.jpa_demo.datasource1.domain.Consumer;
import com.springboot.jpa_demo.datasource1.repository.BookRepository;
import com.springboot.jpa_demo.datasource1.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class ConsumerController {

    @Autowired
    ConsumerService service;

    //为了自动向数据库添加测试数据而引入的
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/api/v1/trainer/test/hateoas")
    public HttpEntity<Consumer> getInfor() {

        int phonePrefix=1881095989;
        int idPrefix=100;
        String location="北京市海淀区北京交通大学";
        String introduction="我的性格类型是";
        int i=1000;
        Book bookInstance = new Book(String.valueOf(i), location + String.valueOf(i), String.valueOf(phonePrefix + i));
        Book bookOK = bookRepository.saveAndFlush(bookInstance);

        //生成一个trainer
        Consumer consumerInstance = new Consumer(String.valueOf(i), String.valueOf(i), String.valueOf(i), introduction, String.valueOf(phonePrefix), bookOK);

        consumerInstance.add(linkTo(methodOn(ConsumerController.class).getInfor()).withSelfRel());


        return new ResponseEntity<>(consumerInstance, HttpStatus.OK);
    }
}
