package com.Jvnyor.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonProducer {

    @Value("${spring.kafka.producer.topic.name}")
    public String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public JsonProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String json) {
        log.info("Sending a message");
        log.info("JSON: {}", json);
        kafkaTemplate.send(topic, json);
    }
}
