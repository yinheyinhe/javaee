package com.springboot.jpa_demo.datasource1.service;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Consumer;

import java.util.List;

public interface ConsumerService {
    List<Consumer> getallTrainer();
    JSONObject addTrainer(Consumer consumer);
}
