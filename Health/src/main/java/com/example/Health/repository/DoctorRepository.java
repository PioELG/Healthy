package com.example.Health.repository;

import com.example.Health.model.Doctor;
import com.example.Health.model.Malade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,String> {
    @Query("SELECT d FROM Doctor d WHERE d.id = ?1")

    Doctor findByDoc(String patient_id);

}
