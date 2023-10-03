package com.example.Health.repository;

import com.example.Health.model.Medicament;
import com.example.Health.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("SELECT n FROM Notification n WHERE n.cible = ?1")
    List<Notification> findByCible(String cible_id);




}
