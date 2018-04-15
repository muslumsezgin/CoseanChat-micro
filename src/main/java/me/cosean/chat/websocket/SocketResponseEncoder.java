package me.cosean.chat.websocket;

import com.google.gson.Gson;
import me.cosean.chat.model.SocketResponse;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class SocketResponseEncoder implements Encoder.Text<SocketResponse> {
    private static Gson gson = new Gson();

    @Override
    public String encode(SocketResponse message) throws EncodeException {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
