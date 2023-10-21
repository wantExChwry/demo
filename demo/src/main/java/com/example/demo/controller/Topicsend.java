package com.example.demo.controller;

import com.example.demo.config.TopicRabbitMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-10-18:12:33
 */
@RestController
@RequestMapping("/topic")
public class Topicsend {
    @Autowired
    private AmqpTemplate amqpTemplate;

    //表示消息绑定的交换机和路由键
    @GetMapping("/sendOne")
    public void sendOne(){
        amqpTemplate.convertAndSend(TopicRabbitMqConfig.TOPIC_CHANGE,TopicRabbitMqConfig.TOPIC_TWO,"sendOne");
    }

    @GetMapping("/sendTwo")
    public void sendTwo(){
        amqpTemplate.convertAndSend(TopicRabbitMqConfig.TOPIC_CHANGE,TopicRabbitMqConfig.TOPIC_TWO,"sendTwo");
    }

}
//test03