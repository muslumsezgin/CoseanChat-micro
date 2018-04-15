package me.cosean.chat.db;

import me.cosean.chat.model.ResponseUser;
import me.cosean.chat.model.SocketResponse;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

public class WebsocketList {

    private static Map<String,Session> websocketSet = null;
    private static SocketResponse socketResponse =new SocketResponse();

    public static Map<String,Session> getInstance() {
        if (websocketSet == null) {
            websocketSet = new HashMap<>();
        }
        return websocketSet;
    }

    public static void broadcast() throws IOException {
        socketResponse.setOnlineSet(OnlineSet.getInstance());
        socketResponse.setUserSet(UserMap.getUserWitoutSets());
        if (Objects.nonNull(websocketSet))
            websocketSet.keySet().forEach(endpoint -> {
                synchronized (endpoint) {
                    try {
                        if(websocketSet.get(endpoint).isOpen()) {
                            socketResponse.setPendingSet(UserMap.getInstance().get(endpoint).getPendingSet());
                            socketResponse.setRequestSet(UserMap.getInstance().get(endpoint).getRequestSet());
                            socketResponse.setConfirmSet(UserMap.getInstance().get(endpoint).getConfirmSet());
                            websocketSet.get(endpoint).getBasicRemote().sendObject(socketResponse);
                        }
                    } catch (IOException | EncodeException e) {
                        e.printStackTrace();
                    }
                }
            });
    }




}
