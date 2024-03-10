package org.example.handler;

import com.google.gson.Gson;
import org.example.model.ChatPayload;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CustomWebSocketHandler extends TextWebSocketHandler {
    Map<String, WebSocketSession> sessionMap = new HashMap<>();
    private Gson gson = new Gson();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionMap.put(session.getId(), session);
        System.out.println(Arrays.toString(sessionMap.entrySet().toArray()));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        ChatPayload chatPayload = gson.fromJson((String) message.getPayload(), ChatPayload.class);
        String receiverSessionId = chatPayload.getReceiverSessionID();

        WebSocketSession webSocketSession = sessionMap.get(receiverSessionId);
        webSocketSession.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        // Remove the WebSocket session when the connection is closed
        sessionMap.remove(session.getId());
    }
}
