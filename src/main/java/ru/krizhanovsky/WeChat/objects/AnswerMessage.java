package ru.krizhanovsky.WeChat.objects;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerMessage {
    private String content;
    private LocalDateTime localDateTime;
    private String dateString;
    private String timeString;
    private long id;

    public AnswerMessage() {
    }

    public AnswerMessage(String content, LocalDateTime localDateTime, Long id) {
        this.content = content;
        this.localDateTime = localDateTime;
        this.dateString = localDateTime.getDayOfMonth() + "." + localDateTime.getMonthValue() + "." + localDateTime.getYear();
        this.timeString = localDateTime.getHour() + ":" + localDateTime.getMinute();
        this.id = id;
    }

}
