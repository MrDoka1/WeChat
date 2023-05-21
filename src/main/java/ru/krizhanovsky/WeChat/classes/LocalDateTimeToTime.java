package ru.krizhanovsky.WeChat.classes;

import java.time.LocalDateTime;

public class LocalDateTimeToTime {

    public static String timeString(LocalDateTime localDateTime) {
        int hourInt = localDateTime.getHour();
        int minutesInt = localDateTime.getMinute();
        String hour;
        String minutes;
        if (hourInt < 10) {
            hour = "0" + hourInt;
        } else {
            hour = String.valueOf(hourInt);
        }
        if (minutesInt < 10) {
            minutes = "0" + minutesInt;
        } else {
            minutes = String.valueOf(minutesInt);
        }

        return hour + ":" + minutes;
    }
}
