package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 吴荣洋
 * @program: vhr
 * @description:
 * @date 2023-10-18:12:50
 */
@Configuration
public class RabbitMqConfig {
    //1.创建队列，2.创建交换机，3.绑定交换机和队列

    @Bean
    public Queue TestDirectQueue(){
        return new Queue("TestDirectQueue");
    }
    @Bean
    public DirectExchange TestDirectExchange(){
        return new DirectExchange("TestDirectExchange",true,true);
    }
    @Bean
    Binding binding(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

    //ces1deddddd


}
