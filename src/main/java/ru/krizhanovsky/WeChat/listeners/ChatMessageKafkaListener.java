package ru.krizhanovsky.WeChat.listeners;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.krizhanovsky.WeChat.classes.ChatMessageKafka;
import ru.krizhanovsky.WeChat.config.KafkaConstants;

@Component
@RequiredArgsConstructor
@EnableKafka
public class ChatMessageKafkaListener {
    private final SimpMessagingTemplate template;
    private final Gson gson;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(ConsumerRecord<String, String> record) {
        ChatMessageKafka message = gson.fromJson(record.value(), ChatMessageKafka.class);
        System.out.println(message.getTimeStamp());

        // Написать фильтрацию на дилог и чат
        /*System.out.println("sending via kafka listener.." + message.getSenderId() + message.getChat());
        template.convertAndSend("/topic/group", message);*/
    }

}
