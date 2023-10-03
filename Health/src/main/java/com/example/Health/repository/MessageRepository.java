package com.example.Health.repository;

import com.example.Health.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m WHERE m.medecin_id = ?1")
    List<Message> findByMedecin(String medecin_id);

    @Query("SELECT m FROM Message m WHERE m.patient_id = ?1")
    List<Message> findByPatient(String patient_id);

    @Query("SELECT m FROM Message m WHERE m.medecin_id = ?1 AND patient_id = ?2")
    List<Message> findByMedPat(String medecin_id, String patient_id);
}
