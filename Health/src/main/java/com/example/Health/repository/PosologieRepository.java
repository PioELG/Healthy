package com.example.Health.repository;

import com.example.Health.model.Medicament;
import com.example.Health.model.Posologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PosologieRepository extends JpaRepository<Posologie,Long> {
    @Query("SELECT p FROM Posologie p WHERE p.medicament_id = ?1")
    List<Posologie> findByMedicament(Long medicament_id);


}
