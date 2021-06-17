package com.springboot.jpa_demo.datasource1.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String sex;
    private String mobile;
    private int age;
    private String password;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "user_gym", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "gym_id"))
    private List<Book> bookList;

    @CreatedDate
    private Date createAt;

    @LastModifiedDate
    private Date updateAt;

    public Product(String name, String sex, String mobile, int age, String password, List<Book> bookList) {
        this.name = name;
        this.sex = sex;
        this.mobile = mobile;
        this.age = age;
        this.password = password;
        this.bookList = bookList;
    }

    public Product() {
    }
}
