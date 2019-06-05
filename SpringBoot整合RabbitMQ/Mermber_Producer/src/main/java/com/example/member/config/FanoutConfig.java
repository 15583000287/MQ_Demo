package com.example.member.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    //邮件队列
    private static final String FANOUT_EMAIL_QUEUE = "fanout_email_queue";
    //短信队列
    private static final String FANOUT_SMS_QUEUE = "fanout_sms_queue";

    //交换机名称
    private static final String EXCHANGE_NAME = "fanoutExchange";

    //1. 定义队列,默认支持持久化
    //定义邮件队列
    @Bean
    public Queue fanoutEmailQueue(){
        return new Queue(FANOUT_EMAIL_QUEUE);
    }

    //定义短信队列
    @Bean
    public Queue fanoutSmsQueue(){
        return new Queue(FANOUT_SMS_QUEUE);
    }


    //2.定义交换机(使用其他交换机类型，直接更改返回值类型即可)
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE_NAME);
    }


    /**
     * 3. 队列（邮件、短信）与交换机绑定
     * 注意：参数名称和定义队列和交换机的方法名称必须一致，默认将参数名作为id去查找是否有对应的方法，类似于传统配置的ref=“xxxID”
     * @param fanoutEmailQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeEamil(Queue fanoutEmailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeSms(Queue fanoutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
    }

}
