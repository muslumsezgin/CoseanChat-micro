package me.cosean.chat.websocket;

import com.google.gson.Gson;
import me.cosean.chat.model.SocketResponse;

import javax.websocket.*;

public class SocketResponseDecoder implements Decoder.Text<SocketResponse> {
    private static Gson gson = new Gson();

    @Override
    public SocketResponse decode(String s) throws DecodeException {
        return gson.fromJson(s, SocketResponse.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
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
