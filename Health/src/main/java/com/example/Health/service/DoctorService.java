package com.example.Health.service;

import com.example.Health.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor Creer(Doctor doctor);
    List<Doctor> Lire();
    boolean exist(String id);
}
