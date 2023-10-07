package com.example.Health.service;

import com.example.Health.model.Malade;
import com.example.Health.model.Message;

import java.util.List;

public interface MessageService {
    Message Creer(Message message);
    List<Message> Lire();
    Message Modifier(Message message, Long id);

    String Supprimer(Long  id);
     void SupprimerMalade(String malade_id);



    List<Message> LireParPat(String patient_id);

    List<Message> LireParMed(String doc);
    List<Message> LireParMP(String medecin_id,String patient_id);
}
