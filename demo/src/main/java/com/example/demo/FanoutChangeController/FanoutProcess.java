package com.example.demo.FanoutChangeController;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.net.SocketTimeoutException;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-10-18:54:11
 */
@Component
@RabbitListener(queues = "fanout.a")
public class FanoutProcess {
    @RabbitHandler
    public void FanoutA(String msg){
        System.out.println(msg);
    }
}

@RabbitListener(queues = "fanout.b")
class FanoutB{
    @RabbitHandler
    public void FanoutProcesss(String msg){
        System.out.println(msg);
    }
}
@RabbitListener(queues = "fanout.c")
class FanoutC{
    @RabbitHandler
    public void FanoutProcesssC(String msg){
        System.out.println(msg);
    }
}