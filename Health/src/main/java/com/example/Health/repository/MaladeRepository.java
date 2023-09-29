package com.example.Health.repository;

import com.example.Health.model.Constante;
import com.example.Health.model.Malade;
import com.example.Health.model.Symptomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaladeRepository extends JpaRepository<Malade,String> {
    @Query("SELECT m FROM Malade m WHERE m.statut = ?1")
    List<Malade> NonTraite(String status);

    @Query("SELECT m FROM Malade m WHERE m.id = ?1")
    List<Malade> findByPatient_id(String patient_id);
}
