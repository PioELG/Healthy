package com.example.Health.service;

import com.example.Health.model.Constantes;

import java.util.List;

public interface ConstantesService {
    Constantes Creer(Constantes constantes);
    List<Constantes> Lire();
    Constantes Modifier (Constantes constantes,Long id);
    String Supprimer(Long id);

//    List<Constantes> LireP(String user_id);
//    Constantes Accepter (Long id);
//   Constantes Rejeter (Long id);
//    String SupprimerAll();
//    void SupprimerBy(String user_id);
}
