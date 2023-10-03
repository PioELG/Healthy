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

    @Query("SELECT m FROM Malade m WHERE m.statut = ?1 AND m.traitant= ?2")
    List<Malade> SousT(String status,String doc);




    @Query("SELECT m FROM Malade m WHERE m.id = ?1")
    List<Malade> findByPatient_id(String patient_id);

    @Query("SELECT m FROM Malade m WHERE m.id = ?1")
    Malade findByPat(String pat_id);

    @Query("SELECT m FROM Malade m WHERE m.id = ?1")
    Malade Single(String patient_id);

    @Query("SELECT m.id FROM Malade m ")
    List<String > Status(String status);
}
