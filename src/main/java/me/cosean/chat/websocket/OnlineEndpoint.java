package me.cosean.chat.websocket;

import me.cosean.chat.db.OnlineSet;
import me.cosean.chat.db.UserMap;
import me.cosean.chat.db.WebsocketList;
import me.cosean.chat.model.SocketResponse;
import me.cosean.chat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/online/{ip}", decoders = SocketResponseDecoder.class, encoders = SocketResponseEncoder.class)
public class OnlineEndpoint {

    private static final Logger log = LoggerFactory.getLogger(OnlineEndpoint.class);

    @OnOpen
    public void onWebSocketConnect(Session session, @PathParam("ip") String ip) throws IOException, EncodeException {
        log.info("Connected with: " + session.getId());
        OnlineSet.getInstance().add(ip);
        WebsocketList.getInstance().put(ip, session);
        User user = UserMap.getInstance().get(ip);
        session.getBasicRemote().sendObject(new SocketResponse(OnlineSet.getInstance(), UserMap.getUserWitoutSets(),
                user.getPendingSet(), user.getRequestSet(), user.getConfirmSet()));
    }

    @OnMessage
    public void onWebSocketMessage(Session session, String message) throws IOException {
        log.info("Message (from " + session.getId() + "): " + message);
        log.info("Datas Online (from " + session.getId() + "): " + OnlineSet.getInstance().toString());
        log.info("Datas Websocket (from " + session.getId() + "): " + WebsocketList.getInstance().toString());

    }

    @OnClose
    public void onWebSocketClose(Session session, @PathParam("ip") String ip) {
        log.info("Closed: " + session.getId());
        OnlineSet.getInstance().remove(ip);

    }

    @OnError
    public void onWebSocketError(Session session, Throwable error) {
        log.info("Error (from " + session.getId() + "): " + error.getMessage());
    }

}

