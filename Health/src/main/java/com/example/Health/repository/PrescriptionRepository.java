package com.example.Health.repository;

import com.example.Health.model.Prescription;
import com.example.Health.model.Symptomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
    @Query("SELECT p FROM Prescription p WHERE p.patient.id = ?1")
    List<Prescription> findByPatient_id(Long patient_id);
}
