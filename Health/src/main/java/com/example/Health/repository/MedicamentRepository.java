package com.example.Health.repository;

import com.example.Health.model.Malade;
import com.example.Health.model.Medicament;
import com.example.Health.model.Posologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
    @Query("SELECT m FROM Medicament m WHERE m.medecin_id = ?1 AND m.patient_id = ?2")
    List<Medicament> findByMedecin(String medecin_id,String patient_id);

    @Query("SELECT m FROM Medicament m WHERE m.patient_id = ?1")
    List<Medicament> findByPatient(String patient_id);
}
