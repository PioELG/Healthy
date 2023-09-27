package com.example.Health.service;

import com.example.Health.model.Conseil;
import com.example.Health.model.RendezVous;

import java.util.List;

public interface RendezVousService {
   RendezVous Creer(RendezVous rendezVous);
    List<RendezVous> Lire();
    RendezVous Modifier (RendezVous rendezVous,Long id);
    String Supprimer(Long id);
    List<RendezVous> LireParMedecin(Long medecin_id);
    List<RendezVous>LireParPatient(Long patient_id);

//    List<Conseil> LireP(String user_id);
//    Conseil Accepter (Long id);
//   Conseil Rejeter (Long id);
//    String SupprimerAll();
//    void SupprimerBy(String user_id);
}
