package com.example.Health.service;

import com.example.Health.model.Medecin;

import java.util.List;

public interface MedecinService {
    Medecin Creer(Medecin medecin);
    List<Medecin> Lire();
    Medecin Modifier(Medecin medecin, Long id);
    String Supprimer(Long id);

    // Vous pouvez également décommenter ces méthodes si nécessaire
    // List<Medecin> LireP(String user_id);
    // Medecin Accepter(Long id);
    // Medecin Rejeter(Long id);
    // String SupprimerAll();
    // void SupprimerBy(String user_id);
}
