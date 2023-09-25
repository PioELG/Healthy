package com.example.Health.repository;

import com.example.Health.model.Medecin;
import com.example.Health.model.Posologie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
}
