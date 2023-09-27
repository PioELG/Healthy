package com.example.Health.repository;

import com.example.Health.model.Prescription;
import com.example.Health.model.RendezVous;
import com.example.Health.model.Symptomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    @Query("SELECT r FROM RendezVous r WHERE r.patient.id = ?1")
    List<RendezVous> findByPatient_id(Long patient_id);
    @Query("SELECT r FROM RendezVous r WHERE r.medecin.id = ?1")
    List<RendezVous> findByMedecin_id(Long patient_id);
}
