package me.cosean.chat.db;

import me.cosean.chat.model.Message;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

public class UserSocketList {

    private static Map<String,Session> userSocketSet = null;

    public static Map<String,Session> getInstance() {
        if (userSocketSet == null) {
            userSocketSet = new HashMap<>();
        }
        return userSocketSet;
    }

    public static void broadcastUsers(String ip ,String ip2,String message) throws IOException,EncodeException {
        Message userMessage = new Message(ip,ip2,message);
        if (Objects.nonNull(userSocketSet))
            userSocketSet.get(ip).getBasicRemote().sendObject(userMessage);
            userSocketSet.get(ip2).getBasicRemote().sendObject(userMessage);
    }
}
