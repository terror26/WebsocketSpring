package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatPayload {
    private String chatMsg;
    private String receiverSessionID; // for simplicity’s sake taken this otherwise take userId and in handler find sessionId for that userId
}
