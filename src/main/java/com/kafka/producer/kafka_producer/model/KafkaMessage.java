package com.kafka.producer.kafka_producer.model;

import lombok.Data;

@Data
public class KafkaMessage {
    private String code;
    private String label;
}
