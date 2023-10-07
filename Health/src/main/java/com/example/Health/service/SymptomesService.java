package com.example.Health.service;

import com.example.Health.model.Medecin;
import com.example.Health.model.Patient;
import com.example.Health.model.Symptomes;

import java.util.List;

public interface SymptomesService {
    Symptomes Creer(Symptomes symptomes);
    List<Symptomes> Lire();
    Symptomes Modifier(Symptomes symptomes, Long id);
    String Supprimer(Long id);
    List<Symptomes> LireParPatient(String patient_id);

    void SupprimerMalade(String malade_id);
}
