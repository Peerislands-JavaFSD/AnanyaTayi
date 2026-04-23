package com.revature.notification.controller;

import com.revature.notification.model.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/send") // client sends here
    @SendTo("/topic/notifications") // broadcast here
    public Notification send(Notification message) {
        return message;
    }
}