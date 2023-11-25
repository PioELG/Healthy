package com.example.Health.controlleur;

import com.example.Health.model.Note;
import com.example.Health.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/send-notification")
public class FirebaseController {
    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/subscribe")
    public void subscribeToNotifications(@RequestBody String token) {
        // Enregistrez le jeton Firebase dans votre base de données ou utilisez-le pour envoyer des notifications.
    }

    @PostMapping("/yes")
    public void sendNotification(@RequestBody String message, @RequestBody String token) {

        firebaseMessagingService.sendNotificationToAll(message,token);
    }

}
