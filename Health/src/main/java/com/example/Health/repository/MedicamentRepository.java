package com.example.Health.repository;

import com.example.Health.model.Medicament;
import com.example.Health.model.Posologie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
}
