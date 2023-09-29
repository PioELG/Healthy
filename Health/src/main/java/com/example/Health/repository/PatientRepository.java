package com.example.Health.repository;

import com.example.Health.model.Patient;
import com.example.Health.model.Posologie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
