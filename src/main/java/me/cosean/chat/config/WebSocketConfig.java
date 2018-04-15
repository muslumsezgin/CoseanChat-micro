package me.cosean.chat.config;

import me.cosean.chat.websocket.ChatEndpoint;
import me.cosean.chat.websocket.OnlineEndpoint;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableAutoConfiguration
public class WebSocketConfig {

    @Bean
    public ChatEndpoint chatEndpoint() {
        return new ChatEndpoint();
    }

    @Bean
    public OnlineEndpoint onlineEndpoint() {
        return new OnlineEndpoint();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
