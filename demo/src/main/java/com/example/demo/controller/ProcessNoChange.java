package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author 吴荣洋
 * @program: vhr
 * @description:
 * @date 2023-10-18:33:16
 */
//@Component：@Component 用于标识一个类是一个可被自动扫描和装配的组件.在 RabbitMQ 的消费者场景中，消费者类通常需要被自动扫描和装配到 Spring 容器中，以便在消费消息时进行实例化和调用.
@Component
//使用 @RabbitListener 注解标记方法，当监听到队列 debug 中有消息时则会进行接收并处理
@RabbitListener(queues = "TestDirectQueue")
public class ProcessNoChange {
    //@RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
    @RabbitHandler
    public void processUser(User user){
        System.out.println(user);
    }


//@RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
    //1. 同一队列中只能有一个@RabbitHandler方法处理 相同类型的消息.@RabbitHandler是根据接受的类型来匹配方法里的参数
    @RabbitHandler
    public void handleMessage(String message) {
        System.out.println("Received string message: " + message);
    }

    @RabbitHandler
    public void handleMessage(Integer message) {
        System.out.println("Received integer message: " + message);
    }


    //2. 使用@Payload注解指定消息体类型
    //在消息处理方法上，我们可以使用@Payload注解指定要接收的消息体类型。如果消息体类型不是简单类型，我们还需要在类上添加@RabbitListener注解指定要监听的队列。
    @RabbitHandler
    public void handleMessage2(@Payload String message) {
        System.out.println("Received string message: " + message);
    }

    @RabbitHandler
    public void handleMessage2(@Payload Integer message) {
        System.out.println("Received integer message: " + message);
    }

//    @RabbitListener(queues = "anotherQueue")
//    @RabbitHandler
//    public void handleMessage2(@Payload Message message) {
//        System.out.println("Received custom message: " + message);
//    }
}
