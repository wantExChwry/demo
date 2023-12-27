package com.example.demo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-10-18:30:27
 */
@Component
@RabbitListener(queues = "topic.two")
public class TopicTotalProcess {
    @RabbitHandler
    public void topicTotal(String msg){
        System.out.println(msg);
    }
}
