package com.example.Health.repository;

import com.example.Health.model.Conseil;
import com.example.Health.model.Posologie;
import com.example.Health.model.Symptomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConseilRepository extends JpaRepository<Conseil,Long> {
    @Query("SELECT co FROM Conseil co WHERE co.medecin_id = ?1")
    List<Conseil> findByMedecin(String medecin_id);
}
