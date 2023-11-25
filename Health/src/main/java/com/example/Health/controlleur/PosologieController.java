package com.example.Health.controlleur;

import com.example.Health.model.Medicament;
import com.example.Health.model.Posologie;
import com.example.Health.model.Symptomes;
import com.example.Health.service.MedicamentService;
import com.example.Health.service.PosologieService;
import com.example.Health.service.SymptomesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(path="/api/posologie")
@AllArgsConstructor
public class PosologieController {
    @Autowired
    private  final PosologieService posologieService;
    @Autowired
    private  final MedicamentService medicamentService;
    @Autowired

    @GetMapping
    public List<Posologie> read()
    {
        return posologieService.Lire();
    }

    @GetMapping("poso/{id}")
    public List<Posologie> readPar(@PathVariable Long id)
    {

        return posologieService.LireP(id);
    }
    @PostMapping
    public Posologie create(@RequestBody Posologie posologie)
    {

        posologie.setStatut("off");
        return posologieService.Creer(posologie);
    }
    @PutMapping("/{id}")
    public Posologie update(@RequestBody Posologie posologie, @PathVariable Long id )
    {
        return posologieService.Modifier(posologie,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return posologieService.Supprimer(id);

    }

//    @GetMapping("/CronMatin")
//    @Scheduled(cron = "* */2 * * * ?")
//    public void CronVerifMatin()
//    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//    }

//    public boolean VerifMatin(Authentication authentication) {
//
//        Jwt jwt = (Jwt) authentication.getPrincipal();
//
//        String id = jwt.getClaimAsString("sub");
//        List<Medicament> medicaments = medicamentService.LireParPat(id);
//
//        int i = 0;
//        List<Posologie> posologies = null;
//        for (Medicament medicament : medicaments) {
//
//            posologies = new ArrayList<>();
//            posologies = posologieService.LireP(medicament.getId());
//        }
//        boolean toutesLesPosologiesSontON = true; // Initialisez à true
//
//        for (Posologie posologie : posologies) {
//            if (posologie.getHeurePrise().equals("matin") && !posologie.getStatut().equals("on")) {
//                toutesLesPosologiesSontON = false;
//                break; // Si l'une des posologies n'est pas "on" le matin, sortir de la boucle
//            }
//        }
//
//        if (toutesLesPosologiesSontON) {
//            // Toutes les posologies du matin ont un statut "on"
//
//            return true;
//        } else {
//            // Au moins une posologie du matin n'a pas le statut "on"
//            System.out.println("cccc");
//            return false;
//
//        }
//
//    }
}
