package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.entity.EmployeeEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic",groupId = "test-group")
    public void listen(String employeeJson) {
        EmployeeEntity employeeEntity = JSON.parseObject(employeeJson, EmployeeEntity.class);
        System.out.println("revord value:"+employeeEntity);
    }

}