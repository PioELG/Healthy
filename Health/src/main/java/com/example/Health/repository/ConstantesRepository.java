package com.example.Health.repository;

import com.example.Health.model.Constante;
import com.example.Health.model.Posologie;
import com.example.Health.model.Symptomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConstantesRepository extends JpaRepository<Constante,Long> {
    @Query("SELECT c FROM Constante c WHERE c.patient_id = ?1")
    List<Constante> findByPatient_id(String patient_id);



}
