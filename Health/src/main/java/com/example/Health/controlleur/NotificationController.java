package com.example.Health.controlleur;

import com.example.Health.model.Doctor;
import com.example.Health.model.Malade;
import com.example.Health.model.Message;
import com.example.Health.model.Notification;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.service.DoctorService;
import com.example.Health.service.MaladeService;
import com.example.Health.service.MessageService;
import com.example.Health.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/notification")

@AllArgsConstructor
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @Autowired
    MaladeRepository maladeRepository;

    @Autowired
    DoctorService doctorService;
    @Autowired
    MaladeService maladeService;


    @GetMapping("/patient")
    public List<Notification> read(Authentication authentication)
    {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");

        Malade malade= new Malade();
        String email = jwt.getClaimAsString("email");
        String nom= jwt.getClaimAsString("family_name");
        String prenom=jwt.getClaimAsString("given_name");
        Object realmAccessClaim = jwt.getClaim("realm_access");

        List<String> roles = null;
        if (realmAccessClaim instanceof Map) {
            Map<String, Object> realmAccess = (Map<String, Object>) realmAccessClaim;


            if (realmAccess.containsKey("roles")) {
                Object rolesClaim = realmAccess.get("roles");

                if (rolesClaim instanceof List) {
                    roles = (List<String>) rolesClaim;


                    for (String role : roles) {
                        System.out.println("Rôle : " + role);
                    }
                }
            }
        }
        if (roles.contains("patient")) {


            if(!maladeRepository.Status(id).contains(id) || maladeRepository.Stat("FinTraitement").contains(id) )
            {   malade.setId(id);
                malade.setEmail(email);
                malade.setNom(nom);
                malade.setPrenom(prenom);

                malade.setStatut("Non traité");

                System.out.println(maladeRepository.Status(id));
                maladeService.Creer(malade);

            }
            //  System.out.println(maladeRepository.Stat("FinTraitement"));
//            else {
//                malade.setStatut("Non traité");
//                maladeService.ModifierS(malade,id);
//            }




        }


        return notificationService.LireC(id,"tous");
    }
    @GetMapping("/doc")
    public List<Notification> readD(Authentication authentication)
    {

        Jwt jwt = (Jwt) authentication.getPrincipal();
        Doctor doctor=new Doctor();
        String id = jwt.getClaimAsString("sub");
        String email = jwt.getClaimAsString("email");
        String nom= jwt.getClaimAsString("family_name");
        String prenom=jwt.getClaimAsString("given_name");
        Object realmAccessClaim = jwt.getClaim("realm_access");

        List<String> roles = null;
        if (realmAccessClaim instanceof Map) {
            Map<String, Object> realmAccess = (Map<String, Object>) realmAccessClaim;

            // Vérifier si la clé "roles" existe dans realmAccess
            if (realmAccess.containsKey("roles")) {
                Object rolesClaim = realmAccess.get("roles");

                if (rolesClaim instanceof List) {
                    roles = (List<String>) rolesClaim;

                    // Maintenant, la liste 'roles' contient les rôles associés à l'utilisateur
                    for (String role : roles) {
                        System.out.println("Rôle : " + role);
                    }
                }
            }
        }
        if (roles.contains("medecin")) {
            doctor.setId(id);
            doctor.setEmail(email);
            doctor.setNom(nom);
            doctor.setPrenom(prenom);
            doctorService.Creer(doctor);
        }


        return notificationService.LireCD(id);
    }



    @PostMapping("/patient")
    public Notification create(@RequestBody Notification notification, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        notification.setInitiateur(id);;
        Malade malade= new Malade();
        malade= maladeRepository.findByPat(id);
        notification.setCible(malade.getTraitant());
        return notificationService.Creer(notification);
    }
    @PostMapping("/doc")
    public Notification createM(@RequestBody Notification notification, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        notification.setInitiateur(id);
        return notificationService.Creer(notification);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return notificationService.Supprimer(id);

    }
    @DeleteMapping("malade/{idM}")
    public void deleteMalade ( @PathVariable String idM){
        notificationService.SupprimerMalade(idM);

    }

}
