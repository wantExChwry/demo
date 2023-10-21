package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-10-18:18:03
 */
//主题模式，可以根据routing_key自由的绑定不同的队列
@Configuration
public class TopicRabbitMqConfig {
    public final static String TOPIC_ONE = "topic.one";
    public final static String TOPIC_TWO = "topic.two";
    public final static String TOPIC_CHANGE = "topicExchange";

    @Bean
    public Queue queueOne(){
        return new Queue(TOPIC_ONE);
    }

    @Bean
    public Queue queueTwo(){
        return new Queue(TOPIC_TWO);
    }

    //生名了交换机的模式和名字
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_CHANGE,true,true);
    }

    /*
    注意看rabbitmq的图例，只要发送消息都会通过交换机，然后再匹配队列
        with("topic.one")表示路由键，交换价与队列相互对应的线，
        1.return BindingBuilder.bind(queueOne()).to(topicExchange()).with("topic.one")表示：
            队列与交换机绑定，连接的方式是主题模式，连接的键是topic.one，交换机通过这个键找到队列也可以匹配对各队列。
            如果将消息的路由键设置为 topic.one，主题交换器将尝试将消息路由到与之匹配的队列。根据您提供的信息，队列名字为 topic.one，与路由键 topic.one 完全匹配。
            因此，交换器会将具有路由键为 topic.one 的消息发送到名为 topic.one 的队列中。
        2.rabbitTemplate.convertAndSend("topicExchange", "topic.one", manMap)表示：
            发送一条消息到名为 "topicExchange" 的主题交换器上，并使用路由键 "topic.man" 进行发送。首先将消息发送到名字为第一个参数的交换机上，
            根据该交换机的模式进行匹配，根据路由哦键进行匹配。因为发送路由键为topic.one，所以交换机匹配了路由键topic.one和topic.#。两个消费者都能接收到消息。
        如果rabbitTemplate.convertAndSend("topicExchange", "topic.two", manMap)，那么不会匹配到路由键topic.one，从而不会找到队列topic.one。
     */

    @Bean
    Binding binding(){
        //# 表示零个或多个词
        //* 表示一个词
        return BindingBuilder.bind(queueOne()).to(topicExchange()).with("topic.one");
    }

    @Bean
    Binding bindingTest(){
        return BindingBuilder.bind(queueTwo()).to(topicExchange()).with("topic.#");
    }



}
