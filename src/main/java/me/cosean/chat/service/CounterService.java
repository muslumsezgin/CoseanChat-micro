package me.cosean.chat.service;

import me.cosean.chat.db.WebsocketList;
import me.cosean.chat.websocket.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CounterService {


    @Scheduled(fixedDelay = 15000)
    public void sendCounterUpdate() {
        WebsocketList.listenAll();
    }


}