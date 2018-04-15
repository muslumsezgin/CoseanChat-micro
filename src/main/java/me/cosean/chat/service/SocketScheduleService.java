package me.cosean.chat.service;

import me.cosean.chat.db.OnlineSet;
import me.cosean.chat.db.WebsocketList;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class SocketScheduleService {


    @Scheduled(fixedDelay = 5000)
    public void socketUpdate() throws IOException {
        WebsocketList.broadcast();
    }





}