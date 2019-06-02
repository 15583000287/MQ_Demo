package com.example.activemqtopic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ActivemqTopicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqTopicApplication.class, args);
    }

}
