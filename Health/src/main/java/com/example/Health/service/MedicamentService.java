package com.example.Health.service;

import com.example.Health.model.Medecin;
import com.example.Health.model.Medicament;

import java.util.List;

public interface MedicamentService {
    Medicament Creer(Medicament medicament);
    List<Medicament> Lire();
    List<Medicament> LireParPat(String patient_id,String Statut);
    List<Medicament> LireParMed(String medecin_id,String patient_id, String Statut);

    List<Medicament> LirePat(String patient_id);
    List<Medicament> LireMed(String medecin_id);

    Medicament ModifierDuree(Medicament medicament, Long id);
    Medicament ModifierStatut(Medicament medicament, Long id);
    String Supprimer(Long id);

    Medicament Unique (Long id);

    // Vous pouvez également décommenter ces méthodes si nécessaire
    // List<Medecin> LireP(String user_id);
    // Medecin Accepter(Long id);
    // Medecin Rejeter(Long id);
    // String SupprimerAll();
    // void SupprimerBy(String user_id);
}
