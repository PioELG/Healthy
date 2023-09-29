package com.example.Health.service;

import com.example.Health.model.Doctor;
import com.example.Health.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Doctor Creer(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> Lire() {
        return doctorRepository.findAll();
    }

    @Override
    public boolean exist(String id) {
        return doctorRepository.existsById(id);
    }



}
