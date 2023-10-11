package com.example.Health.repository;

import com.example.Health.model.Antecedent;
import com.example.Health.model.ModeleMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AntecedentRepository extends JpaRepository<Antecedent,Long> {
    @Query("SELECT a FROM Antecedent a ORDER BY a.nom ASC")
    List<Antecedent> OrderB();
}
