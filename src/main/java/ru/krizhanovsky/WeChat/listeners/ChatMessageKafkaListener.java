package ru.krizhanovsky.WeChat.listeners;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.krizhanovsky.WeChat.classes.ChatMessageKafka;
import ru.krizhanovsky.WeChat.classes.MessageOutput;
import ru.krizhanovsky.WeChat.config.KafkaConstants;
import ru.krizhanovsky.WeChat.servises.MessageService;

@Component
@RequiredArgsConstructor
@EnableKafka
public class ChatMessageKafkaListener {
    private final SimpMessagingTemplate template;
    private final Gson gson;
    private final MessageService messageService;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(ConsumerRecord<String, String> record) {
        ChatMessageKafka message = gson.fromJson(record.value(), ChatMessageKafka.class);
        MessageOutput messageOutput = new MessageOutput(messageService.addMessage(message));

        // Фильтрация на дилог и чат
        if (message.getChat().charAt(0) == 'c') {
            template.convertAndSend(("/topic/chat/" + message.getChat()), messageOutput);
        } else {
            String topic = Math.min(message.getSenderId(), Long.parseLong(message.getChat())) + "_" +
                    Math.max(message.getSenderId(), Long.parseLong(message.getChat()));
            template.convertAndSend(("/topic/dialog/" + topic), messageOutput);
        }
    }

}
