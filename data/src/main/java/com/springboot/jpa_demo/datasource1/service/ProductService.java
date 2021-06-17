package com.springboot.jpa_demo.datasource1.service;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Product;

public interface ProductService {

    JSONObject addUser(Product product);

    JSONObject login(int id,String password);

    Product findUserById(int userId);
}
