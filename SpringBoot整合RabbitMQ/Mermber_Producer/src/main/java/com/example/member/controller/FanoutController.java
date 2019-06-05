package com.example.member.controller;

import com.example.member.producer.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutController {
    @Autowired
    private FanoutProducer fanoutProducer;

    @GetMapping("/sendMsg")
    public String sendMsg(String queueName){
        fanoutProducer.send(queueName);
        return "success!";
    }
}
