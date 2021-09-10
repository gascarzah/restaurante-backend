package com.gafahtec.socket.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

	
    public void sendMessage(final String topicSuffix) {
    	simpMessagingTemplate.convertAndSend("/topic/" + topicSuffix, "Default message from our WS service");
    }


}
