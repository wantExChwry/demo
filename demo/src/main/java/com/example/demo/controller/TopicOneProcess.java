package com.example.demo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-10-18:12:45
 */
@Component
@RabbitListener(queues = "topic.one")
public class TopicOneProcess {
    //发送的是什么类型就接收什么类型
    @RabbitHandler
    public void topicOnePress(String msg){
        System.out.println(msg);
    }
}
