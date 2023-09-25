package com.example.Health.service;

import com.example.Health.model.Medecin;
import com.example.Health.model.Patient;

import java.util.List;

public interface PatientService {
    Patient Creer(Patient patient);
    List<Patient> Lire();
    Patient Modifier(Patient patient, Long id);
    String Supprimer(Long id);

    // Vous pouvez également décommenter ces méthodes si nécessaire
    // List<Medecin> LireP(String user_id);
    // Medecin Accepter(Long id);
    // Medecin Rejeter(Long id);
    // String SupprimerAll();
    // void SupprimerBy(String user_id);
}
