package com.zhuwl.springcloud.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic stockUpdatedTopic() {
        return new NewTopic("stock-updated-topic", 2, (short) 1); // 2个分区，每个分区1个副本
    }
}
