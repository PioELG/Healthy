package com.example.Health.repository;

import com.example.Health.model.Medicament;
import com.example.Health.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("SELECT n FROM Notification n WHERE n.patient_id = ?1")
    List<Notification> findByPatient(String patient_id);

    @Query("SELECT n FROM Notification n WHERE n.medecin_id = ?1")
    List<Notification> findByMedecin(String patient_id);


}
