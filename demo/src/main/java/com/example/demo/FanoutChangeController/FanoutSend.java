package com.example.demo.FanoutChangeController;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-10-18:48:34
 */

@RestController
@RequestMapping("/fanout")
public class FanoutSend {
    @Autowired
    AmqpTemplate amqpTemplate;

    @GetMapping("/changeA")
    public void fanoutChange(){
        amqpTemplate.convertAndSend("FanoutEXChange",null,"fanout all");
    }
}
