package com.example.Health.repository;

import com.example.Health.model.Message;
import com.example.Health.model.ModeleMedicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeleMedicamentRepository extends JpaRepository<ModeleMedicament,Long> {
}
