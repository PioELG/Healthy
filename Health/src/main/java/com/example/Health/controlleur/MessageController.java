package com.example.Health.controlleur;

import com.example.Health.model.*;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/message")
@AllArgsConstructor
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    MaladeRepository maladeRepository;

    @GetMapping("/PatientMP")
    public List<Message> readMP(Authentication authentication)
    {
        Malade malade=new Malade();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        malade=maladeRepository.findByPat(id);

        return messageService.LireParMP(malade.getTraitant(),id);
    }
    @GetMapping("/MedecinMP/{idP}")
    public List<Message> read(@PathVariable String idP,  Authentication authentication)
    {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");


        return messageService.LireParMP(id,idP);
    }
    @GetMapping("/Patient")
    public List<Message> readP(Authentication authentication)
    {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");


        return messageService.LireParPat(id);
    }
    @GetMapping
    public List<Message> readAll(Authentication authentication)
    {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");


        return messageService.LireParMed(id);
    }

    @PostMapping("/patient")
    public Message create(@RequestBody Message message, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
       message.setPatient_id(id);
       message.setSender("Patient");
        Malade malade= new Malade();
        malade= maladeRepository.findByPat(id);
        message.setMedecin_id(malade.getTraitant());
        return messageService.Creer(message);
    }
    @PostMapping("/doc")
    public Message createM(@RequestBody Message message, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        message.setMedecin_id(id);
        message.setSender("Medecin");
        return messageService.Creer(message);
    }
    @DeleteMapping("/{id}")
    public String SupprimerM(@PathVariable Long id)
    {
        return messageService.Supprimer(id);
    }

}
