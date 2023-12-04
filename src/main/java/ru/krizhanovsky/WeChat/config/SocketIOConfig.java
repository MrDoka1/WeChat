package ru.krizhanovsky.WeChat.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {
    @Value("${socket.hostname}")
    private String hostname;

    @Value("${socket.port}")
    private Integer port;

    @Value("${socket.upgrade-timeout}")
    private Integer upgradeTimeout;

    @Value("${socket.ping-interval}")
    private Integer pingInterval;

    @Value("${socket.ping-timeout}")
    private Integer pingTimeout;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        // Set the host name, the default is 0.0.0.0
        config.setHostname(hostname);
                 // Set the listener port
        config.setPort(port);
        // HTTP protocol Upgrade WS timeout (ms), default 10000
        config.setUpgradeTimeout(upgradeTimeout);
                 // The client sends the interval of the heartbeat to the server, the default 25000
        config.setPingInterval(pingInterval);
        // The timeout time of sending messages to the server, default 60000
        config.setPingTimeout(pingTimeout);
        // jwt token verification
        /*config.setAuthorizationListener(data -> {
            String token = data.getSingleUrlParam("token");
            if (null != token) {
                return true;
            }
            return false;
        });*/
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

}
