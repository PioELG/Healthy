package com.example.Health.repository;

import com.example.Health.model.Symptomes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SymptomesRepository extends JpaRepository <Symptomes,Long> {
    @Query("SELECT s FROM Symptomes s WHERE s.patient_id = ?1")
    List<Symptomes> findByPatient_id(String patient_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Symptomes s  WHERE s.patient_id =?1")

    void deleteByMalade_id(String malade_id);
}
