package com.example.Health.service;

import com.example.Health.model.Note;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    public static void sendNotificationToAll(String message, String token) {
        Message fcmMessage = Message.builder()
                .putData("message", message)
                .setNotification(Notification.builder().setBody(message).build())
                .setToken(token)
                .build();

        try {
            FirebaseMessaging.getInstance().send(fcmMessage);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }

}
