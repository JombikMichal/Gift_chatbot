package com.hack.controller;

import com.hack.model.ChatMessage;
import com.hack.service.AIMLService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private ChatMessage chatMessage;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws InterruptedException {
        this.chatMessage = chatMessage;
        return chatMessage;
    }

    @MessageMapping("/chat.sendMessageRobot")
    @SendTo("/topic/public")
    public ChatMessage sendMessageRobot() {

        AIMLService aimlService = new AIMLService();
        ChatMessage chatMessageRobot = new ChatMessage();
        chatMessageRobot.setSender("Robot");
        chatMessageRobot.setContent(aimlService.bot(this.chatMessage.getContent()));
        return chatMessageRobot;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
