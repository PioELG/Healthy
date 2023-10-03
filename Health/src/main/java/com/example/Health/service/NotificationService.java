package com.example.Health.service;

import com.example.Health.model.Malade;
import com.example.Health.model.Notification;

import java.util.List;

public interface NotificationService {
    Notification Creer(Notification notification);
    List<Notification> LireC(String cible_id);

    List<Notification> Lire();

    Notification Modifier(Notification notification, Long id);

    String Supprimer(Long  id);
}
