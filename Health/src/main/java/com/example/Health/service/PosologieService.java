package com.example.Health.service;

import com.example.Health.model.Medecin;
import com.example.Health.model.Posologie;

import java.util.List;

public interface PosologieService {
    Posologie Creer(Posologie posologie);
    List<Posologie> Lire();

    List<Posologie> LireP(Long id);
    Posologie Modifier(Posologie posologie, Long id);
    String Supprimer(Long id);



    // Vous pouvez également décommenter ces méthodes si nécessaire
    // List<Medecin> LireP(String user_id);
    // Medecin Accepter(Long id);
    // Medecin Rejeter(Long id);
    // String SupprimerAll();
    // void SupprimerBy(String user_id);
}
