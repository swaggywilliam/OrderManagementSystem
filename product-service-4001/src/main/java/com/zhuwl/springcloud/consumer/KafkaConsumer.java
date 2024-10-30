package com.zhuwl.springcloud.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "stock-updated-topic")
    public void Listener(String msg) {
        System.out.println("接收到消息：" + msg);
    }
}
