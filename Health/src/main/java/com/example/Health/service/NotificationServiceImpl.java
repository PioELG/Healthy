package com.example.Health.service;

import com.example.Health.model.Message;
import com.example.Health.model.Notification;
import com.example.Health.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification Creer(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> LireC(String cible_id) {
        return notificationRepository.findByCible(cible_id);
    }



    @Override
    public List<Notification> Lire() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification Modifier(Notification notification, Long id) {
        return null;
    }

    @Override
    public String Supprimer(Long id) {
        notificationRepository.deleteById(id);
        return "Bien supprimé";
    }
}
