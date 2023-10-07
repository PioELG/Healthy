package com.example.Health.service;

import com.example.Health.model.Constante;


import java.util.List;

public interface ConstantesService {
    Constante Creer(Constante constantes);
    List<Constante> Lire();
    Constante Modifier (Constante constantes,Long id);
    String Supprimer(Long id);

    void SupprimerMalade(String malade_id);

  List<Constante> LireP(String user_id);

//    Constantes Accepter (Long id);
//   Constantes Rejeter (Long id);
//    String SupprimerAll();
//    void SupprimerBy(String user_id);
}
