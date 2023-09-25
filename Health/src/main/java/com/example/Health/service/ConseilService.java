package com.example.Health.service;

import com.example.Health.model.Conseil;

import java.util.List;

public interface ConseilService {
    Conseil Creer(Conseil conseil);
    List<Conseil> Lire();
    Conseil Modifier (Conseil conseil,Long id);
    String Supprimer(Long id);

//    List<Conseil> LireP(String user_id);
//    Conseil Accepter (Long id);
//   Conseil Rejeter (Long id);
//    String SupprimerAll();
//    void SupprimerBy(String user_id);

}
