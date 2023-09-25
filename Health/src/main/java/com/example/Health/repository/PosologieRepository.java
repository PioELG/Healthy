package com.example.Health.repository;

import com.example.Health.model.Posologie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosologieRepository extends JpaRepository<Posologie,Long> {
}
