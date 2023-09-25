package com.example.Health.repository;

import com.example.Health.model.Symptomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SymptomesRepository extends JpaRepository <Symptomes,Long> {
    @Query("SELECT s FROM Symptomes s WHERE s.patient.id = ?1")
    List<Symptomes> findByPatient_id(Long patient_id);
}
