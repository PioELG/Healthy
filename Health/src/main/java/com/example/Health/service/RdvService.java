package com.example.Health.service;

import com.example.Health.model.Malade;
import com.example.Health.model.Rdv;

import java.util.List;

public interface RdvService {
    Rdv Creer(Rdv rdv);
    List<Rdv> Lire();
    List<Rdv> LireP(String patient_id);
    List<Rdv> LireM(String medecin_id);

    Rdv Modifier(Rdv rdv, Long id);
    String Supprimer(Long id);

}
