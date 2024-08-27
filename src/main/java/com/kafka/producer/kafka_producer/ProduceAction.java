package com.kafka.producer.kafka_producer;

import com.kafka.producer.kafka_producer.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ProduceAction {
    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;
    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    long j = 0;

    @Scheduled(cron = "0 */3 * * * *")
    @Async
    public void producing() {
        for (long i = 0; i < 10000; i++) {
            log.info("create " + (i + j * 10000 + 1));
            KafkaMessage message = new KafkaMessage();
            message.setCode("Message " + (i + j * 10000 + 1));
            message.setLabel("Message Content " + (i + j * 10000 + 1));
            kafkaTemplate.send(topicName, message);
        }
        j++;
    }
}
