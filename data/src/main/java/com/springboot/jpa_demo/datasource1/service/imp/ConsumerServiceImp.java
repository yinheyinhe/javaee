package com.springboot.jpa_demo.datasource1.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Consumer;
import com.springboot.jpa_demo.datasource1.repository.ConsumerRepository;
import com.springboot.jpa_demo.datasource1.service.ConsumerService;
import com.springboot.jpa_demo.utils.ConstantVar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImp implements ConsumerService {
    @Autowired
    ConsumerRepository consumerRepository;

    @Override
    public List<Consumer> getallTrainer() {
        return consumerRepository.findAll();
    }

    @Override
    public JSONObject addTrainer(Consumer consumer) {
        JSONObject res=new JSONObject();
        Consumer consumer1 = consumerRepository.save(consumer);

        res.put("data", consumer1);
        res.put("code", ConstantVar.SUCCESSFUL_CODE);
        res.put("message",ConstantVar.SUCCESSFUL_MESSAGE);
        return res;
    }
}
