package ru.krizhanovsky.WeChat.config;

import org.springframework.stereotype.Component;

@Component
public class KafkaConstants {
    public static final String KAFKA_TOPIC = "kafka-chat-messages";
    public static final String GROUP_ID = "app.1";

}
