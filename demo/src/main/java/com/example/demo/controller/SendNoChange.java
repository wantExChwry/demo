package com.example.demo.controller;


import com.example.demo.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author 吴荣洋
 * @program: vhr
 * @description:
 * @date 2023-10-18:26:25
 */

@RestController
public class SendNoChange {
    @Autowired
    private AmqpTemplate amqpTemplate;

    //convertAndSend中，加入发送消息的队列
    public void send(){
        String msg = "hello" + LocalDateTime.now().toString();
        amqpTemplate.convertAndSend("TestDirectQueue",msg);
    }

    public void sendObject(User user){
        user.setUserId("21");
        user.setAge(12);
        amqpTemplate.convertAndSend("TestDirectQueue",user);
    }
}
