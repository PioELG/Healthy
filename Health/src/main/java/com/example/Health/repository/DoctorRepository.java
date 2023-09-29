package com.example.Health.repository;

import com.example.Health.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,String> {

}
