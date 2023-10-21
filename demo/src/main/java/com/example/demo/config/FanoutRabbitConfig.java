package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-10-18:36:17
 */
//Fanout Exchang 扇型交换机,全局广播。给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
@Configuration
public class FanoutRabbitConfig {
    public final static String FANOUT_A ="fanout.a";
    public final static String FANOUT_B ="fanout.b";
    public final static String FANOUT_C ="fanout.c";
    public final static String FANOUT_EXCAHNGE ="FanoutEXChange";

    @Bean
    public Queue FanoutQueueA(){
        return new Queue(FANOUT_A);
    }

    @Bean
    public Queue FanoutQueueB(){
        return new Queue(FANOUT_B);
    }
    @Bean
    public Queue FanoutQueueC(){
        return new Queue(FANOUT_C);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCAHNGE);
    }

    @Bean
    Binding bindingExchangeA(){
        return BindingBuilder.bind(FanoutQueueA()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeB(){
        return BindingBuilder.bind(FanoutQueueB()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeC(){
        return BindingBuilder.bind(FanoutQueueC()).to(fanoutExchange());
    }


}
