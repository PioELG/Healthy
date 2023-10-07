package com.example.Health.repository;

import com.example.Health.model.Medicament;
import com.example.Health.model.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("SELECT n FROM Notification n WHERE n.cible = ?1 OR n.cible = ?2 ")
    List<Notification> findByCible(String cible_id,String to);

    @Query("SELECT n FROM Notification n WHERE n.cible = ?1  ")
    List<Notification> findByCibleD(String cible_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Notification n  WHERE n.cible =?1")

    void deleteByMalade_id(String malade_id);



}
