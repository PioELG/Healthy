package com.example.Health.repository;

import com.example.Health.model.Medicament;
import com.example.Health.model.Message;
import com.example.Health.model.ModeleMedicament;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModeleMedicamentRepository extends JpaRepository<ModeleMedicament,Long> {
    @Query("SELECT m FROM ModeleMedicament m ORDER BY m.nom ASC")
    Page<ModeleMedicament> OrderB(Pageable pageable);
}
