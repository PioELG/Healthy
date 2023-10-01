package com.example.Health.repository;

import com.example.Health.model.Rdv;
import com.example.Health.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RdvRepository extends JpaRepository<Rdv,Long> {
    @Query("SELECT r FROM Rdv r WHERE r.malade_id = ?1")
    List<Rdv> findByMalade_id(String  malade_id);
    @Query("SELECT r FROM Rdv r WHERE r.doctor_id = ?1")

    List<Rdv> findByDoctor_id(String doctor_id);
}
