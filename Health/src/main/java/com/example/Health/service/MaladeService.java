package com.example.Health.service;

import com.example.Health.model.Doctor;
import com.example.Health.model.Malade;
import com.example.Health.model.Patient;

import java.util.List;

public interface MaladeService {
    Malade Creer(Malade malade);
    List<Malade> Lire();
    Malade Modifier(Malade malade, String id);

    Malade ModifierFT(Malade malade, String id);

    String Supprimer(String  id);
    List<Malade> LireNT();
    List<Malade> LireST(String doc);
    List<Malade> LireParPat(String patient_id);

    Malade ModifierS(Malade malade, String id);

    boolean exist(String id);
}
