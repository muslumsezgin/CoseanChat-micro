package me.cosean.chat.db;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebsocketList {

    private static List<WebSocketSession> websocketSet = null;

    public static List<WebSocketSession> getInstance() {
        if (websocketSet == null) {
            websocketSet = new ArrayList<>();
        }
        return websocketSet;
    }

    public static void listenAll(){
        try {
            for(WebSocketSession webSocketSession : websocketSet) {
                webSocketSession.sendMessage(new TextMessage(OnlineSet.getInstance().toString()));
            }
        }catch (Exception e){}

    }
}
