package ru.krizhanovsky.WeChat.interfaces;

public interface SocketIOServiceInterface {
        /**
         * Начать сервис
         */
        void start();

        /**
         * Нет сервиса
         */
        void stop();

        /**
         * Нажмите информацию к указанному клиенту
         *
         * @Param userid: клиент Уникальная идентичность
         * @Param msgcontent: Содержание сообщения
         */
        void pushMessageToUser(String userId, String msgContent);
}
