package com.example.Health.service;

import com.example.Health.model.Medecin;
import com.example.Health.model.Medicament;

import java.util.List;

public interface MedicamentService {
    Medicament Creer(Medicament medicament);
    List<Medicament> Lire();
    Medicament Modifier(Medicament medicament, Long id);
    String Supprimer(Long id);

    // Vous pouvez également décommenter ces méthodes si nécessaire
    // List<Medecin> LireP(String user_id);
    // Medecin Accepter(Long id);
    // Medecin Rejeter(Long id);
    // String SupprimerAll();
    // void SupprimerBy(String user_id);
}
