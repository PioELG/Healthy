package com.example.Health.repository;

import com.example.Health.model.Antecedent;
import com.example.Health.model.ModeleMedicament;
import com.example.Health.model.Pathologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PathologieRepository extends JpaRepository<Pathologie,Long> {
    @Query("SELECT p FROM Pathologie p ORDER BY p.nom ASC")
    List<Pathologie> OrderB();
}
