package com.example.Health.controlleur;

import com.example.Health.model.Doctor;
import com.example.Health.model.Malade;
import com.example.Health.model.Message;
import com.example.Health.model.Posologie;
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

    @GetMapping("/Medecin")
    public List<Message> read(Authentication authentication)
    {
        Malade malade=new Malade();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        malade=maladeRepository.findByPat(id);

        return messageService.LireParMP(malade.getTraitant(),id);
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

    @PostMapping
    public Message create(@RequestBody Message message)
    {

        return messageService.Creer(message);
    }

}
