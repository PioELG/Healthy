package com.example.Health.controlleur;

import com.example.Health.model.Malade;
import com.example.Health.model.Message;
import com.example.Health.model.Notification;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.service.MessageService;
import com.example.Health.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/notification")

@AllArgsConstructor
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @Autowired
    MaladeRepository maladeRepository;


    @GetMapping("/patient")
    public List<Notification> read(Authentication authentication)
    {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");


        return notificationService.LireP(id);
    }
    @GetMapping("/doc")
    public List<Notification> readD(Authentication authentication)
    {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");


        return notificationService.LireM(id);
    }



    @PostMapping("/patient")
    public Notification create(@RequestBody Notification notification, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        notification.setPatient_id(id);
        Malade malade= new Malade();
        malade= maladeRepository.findByPat(id);
        return notificationService.Creer(notification);
    }
    @PostMapping("/doc")
    public Notification createM(@RequestBody Notification notification, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        notification.setMedecin_id(id);
        return notificationService.Creer(notification);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return notificationService.Supprimer(id);

    }

}
