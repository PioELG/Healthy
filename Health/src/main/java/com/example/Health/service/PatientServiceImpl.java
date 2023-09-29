package com.example.Health.service;

import com.example.Health.model.Medecin;
import com.example.Health.model.Patient;
import com.example.Health.repository.PatientRepository;
import com.example.Health.repository.SymptomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Patient Creer(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> Lire() {
        return null;
    }

    @Override
    public Patient Modifier(Patient patient, Long id) {

        return patientRepository.findById(id).map(m->{
            m.setNom(patient.getNom());
            m.setPrenom(patient.getPrenom());
            m.setAge(patient.getAge());
            m.setEmail(patient.getEmail());
            m.setStatut(m.getStatut());

            return patientRepository.save(m);

        }).orElseThrow(()-> new RuntimeException("medecin non trouvé"));
    }

    @Override
    public String Supprimer(Long id) {
        patientRepository.deleteById(id);
        return "Patient bien supprimé" ;
    }


}
