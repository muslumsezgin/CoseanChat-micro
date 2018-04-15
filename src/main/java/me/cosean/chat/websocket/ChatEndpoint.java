package me.cosean.chat.websocket;

import me.cosean.chat.db.OnlineSet;
import me.cosean.chat.db.UserSocketList;
import me.cosean.chat.db.WebsocketList;
import me.cosean.chat.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ServerEndpoint(value = "/chat", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {

    private static final Logger log = LoggerFactory.getLogger(ChatEndpoint.class);

    @OnOpen
    public void onWebSocketConnect(Session session) {
        log.info("Connected with: " + session.getId());
        Map<String, List<String>> params = session.getRequestParameterMap();
        if (params.get("ip") != null) {
            UserSocketList.getInstance().put(params.get("ip").get(0),session);
        }

    }

    @OnMessage
    public void onWebSocketMessage(Session session, Message message) throws IOException, EncodeException {
        log.info("Message (from " + session.getId() + "): " + message.toString());
        Map<String, List<String>> params = session.getRequestParameterMap();
        if (params.get("ip") != null && Objects.nonNull(UserSocketList.getInstance().get(message.getTo()))
                && Objects.nonNull(UserSocketList.getInstance().get(message.getFrom()))) {
            UserSocketList.broadcastUsers(message.getFrom(),message.getTo(),message.getContent());
        }

    }

    @OnClose
    public void onWebSocketClose(Session session,@PathParam("ip") String ip) {
        log.info("Closed: " + session.getId());
        OnlineSet.getInstance().remove(ip);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable error) {
        log.info("Error (from " + session.getId() + "): " + error.getMessage());
    }

}

