package com.example.Health.service;

import com.example.Health.model.Message;
import com.example.Health.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageRepository messageRepository;
    @Override
    public Message Creer(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> Lire() {
        return messageRepository.findAll();
    }

    @Override
    public Message Modifier(Message message, Long id) {
        return null;
    }

    @Override
    public String Supprimer(Long id) {
         messageRepository.deleteById(id);
         return "message bien supprimé";
    }

    @Override

    public void Cron() {

        System.out.println("bonjour");
    }


    @Override
    public List<Message> LireParPat(String patient_id) {
        return messageRepository.findByPatient(patient_id) ;
    }

    @Override
    public List<Message> LireParMed(String doc) {
        return messageRepository.findByMedecin(doc);
    }

    @Override
    public List<Message> LireParMP(String medecin_id,String patient_id) {
        return messageRepository.findByMedPat(medecin_id,patient_id);
    }
}
