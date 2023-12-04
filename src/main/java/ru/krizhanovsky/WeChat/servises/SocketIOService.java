package ru.krizhanovsky.WeChat.servises;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.krizhanovsky.WeChat.classes.MessageOutput;
import ru.krizhanovsky.WeChat.interfaces.SocketIOServiceInterface;
import ru.krizhanovsky.WeChat.socket.SocketMessage;
import ru.krizhanovsky.WeChat.socket.SocketMessageType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketIOService {

    public void sendMessage(String room,String eventName, SocketIOClient senderClient, String message) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            /*if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName,
                        new SocketMessage(SocketMessageType.SERVER, message, room));
            }*/
            client.sendEvent(eventName,
                    new SocketMessage(SocketMessageType.SERVER, message, room));
        }
    }

}

/*@Service(value = "socketIOService")
@RequiredArgsConstructor
public class SocketIOService implements SocketIOServiceInterface {
    *//**
     * Хранить подключенный клиент
     *//*
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    *//**
     * Пользовательское событие `push_data_event`, используемое для связи сервера с клиентским коммущением
     *//*
    private static final String PUSH_DATA_EVENT = "push_data_event";

    @Autowired
    private SocketIOServer socketIOServer;

    *//**
     * Весеннее создание контейнера IOC IOC, начните после загрузки SocketIOServiceInterface Bean
     *//*
    @PostConstruct
    private void autoStartup() {
        start();
    }

    *//**
     * Весенний контейнер IOC отключен перед уничтожением компонента SocketIOServiceInterface, избегая проблемы перезапуска порта службы проекта
     *//*
    @PreDestroy
    private void autoStop() {
        stop();
    }

    @Override
    public void start() {
        // Слушай клиентское соединение
        socketIOServer.addConnectListener(client -> {
            // Пользовательские события «подключены» -> Связь с клиентами (вы также можете использовать встроенные события, такие как Socket.Event_Connect)
            client.sendEvent("connected", "«Вы успешно подключили это ...»");
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.put(userId, client);
            }
        });

        // Слушайте клиента, чтобы открыть соединение
        socketIOServer.addDisconnectListener(client -> {
            String clientIp = getIpByClient(client);
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.remove(userId);
                client.disconnect();
            }
        });

        // Custom event `Client_info_Event` -> Слушающий клиентское сообщение
        socketIOServer.addEventListener(PUSH_DATA_EVENT, String.class, (client, data, ackSender) -> {
            // Клиент Когда клиент нажимает событие `client_info_event`, ondata принимает данные, вот данные строки JSON, могут также быть байтом [], объект других типов
            String clientIp = getIpByClient(client);
        });

        // начать сервис
        socketIOServer.start();

        // Вещатель: по умолчанию необходимо транслировать все соединения сокетов, но не включает сам отправителя. Если вы также предназначены для получения сообщения, вам нужно отправить себя отдельно.
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    // Отправить широковещательное сообщение каждые 3 секунды
                    Thread.sleep(3000);
                    socketIOServer.getBroadcastOperations().sendEvent("myBroadcast", "Вещательные новости" + LocalDateTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushMessageToUser(String userId, String msgContent) {
        SocketIOClient client = clientMap.get(userId);
        if (client != null) {
            client.sendEvent(PUSH_DATA_EVENT, msgContent);
        }
    }

    *//**
     * Получите параметры идентификатора пользователя в URL-адресе клиента (здесь вы можете изменить в соответствии с вашими личными потребностями и клиентом)
     *
     * @Param клиент: клиент
     * @return: java.lang.String
     *//*
    private String getParamsByClient(SocketIOClient client) {
        // Получить параметры URL клиента (UserID здесь уникален)
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get("userId");
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

    *//**
     * Получите IP-адрес клиента соединения
     *
     * @Param клиент: клиент
     * @return: java.lang.String
     *//*
    private String getIpByClient(SocketIOClient client) {
        String sa = client.getRemoteAddress().toString();
        return sa.substring(1, sa.indexOf(":")); // clientIp
    }
}*/
