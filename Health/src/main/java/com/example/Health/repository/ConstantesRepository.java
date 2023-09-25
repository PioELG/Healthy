package com.example.Health.repository;

import com.example.Health.model.Constantes;
import com.example.Health.model.Posologie;
import com.example.Health.model.Symptomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConstantesRepository extends JpaRepository<Constantes,Long> {
    @Query("SELECT c FROM Constantes c WHERE c.patient.id = ?1")
    List<Constantes> findByPatient_id(Long patient_id);
}
