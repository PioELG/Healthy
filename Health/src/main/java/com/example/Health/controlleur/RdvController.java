package com.example.Health.controlleur;

import com.example.Health.model.Malade;
import com.example.Health.model.Rdv;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.service.EmailModifRdv;
import com.example.Health.service.EmailServiceRdv;
import com.example.Health.service.RdvService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(path="/api/rdv")
@AllArgsConstructor
public class RdvController {
    @Autowired
    private RdvService rdvService;

    @Autowired
    EmailServiceRdv emailServiceRdv;

    @Autowired
    EmailModifRdv emailModifRdv;

    @Autowired
    private MaladeRepository maladeRepository;
//    @GetMapping
//    public List<Rdv> read()
//    {
//        return rdvService.Lire();
//    }
    @GetMapping
    public List<Rdv> readParPatient( Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String idDoc = jwt.getClaimAsString("sub");

        return rdvService.LireM(idDoc);
    }
    @GetMapping("/{id}")
    public List<Rdv> readParPat(@PathVariable String id)
    {

        return rdvService.LireP(id);
    }
    @GetMapping("/patient")
    public List<Rdv> readParMedecin(Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String idP = jwt.getClaimAsString("sub");
        return rdvService.LireP(idP);
    }
    @PostMapping()
    public Rdv create(@RequestBody Rdv rdv, Authentication authentication) throws jakarta.mail.MessagingException
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String idDoc = jwt.getClaimAsString("sub");

        rdv.setDoctor_id(idDoc);

        rdvService.Creer(rdv);

        Malade malade= new Malade();
        malade= maladeRepository.Single(rdv.getMalade_id());




        emailServiceRdv.sendEmail(malade.getNom(),malade.getPrenom(),malade.getEmail(), rdv.getDate(), rdv.getHeure());

        return  rdv;
    }
    @PutMapping("/{id}")
    public Rdv update(@RequestBody Rdv rdv, @PathVariable Long id )throws jakarta.mail.MessagingException
    {
        rdv=rdvService.Modifier(rdv,id);
        Malade malade;
        malade= maladeRepository.Single(rdv.getMalade_id());

         emailModifRdv.sendEmail(malade.getNom(),malade.getPrenom(),malade.getEmail(), rdv.getDate(), rdv.getHeure());

        return rdv;
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long  id){
        return rdvService.Supprimer(id);

    }
    @DeleteMapping("malade/{id}")
    public void deleteMalade ( @PathVariable String id){
         rdvService.SupprimerMalade(id);

    }
}
