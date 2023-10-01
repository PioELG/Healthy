package com.example.Health.controlleur;

import com.example.Health.model.Rdv;
import com.example.Health.service.RdvService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/api/rdv")
@AllArgsConstructor
public class RdvController {
    @Autowired
    private RdvService rdvService;
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
    @GetMapping("/{idP}")
    public List<Rdv> readParMedecin(@PathVariable String idP)
    {

        return rdvService.LireP(idP);
    }
    @PostMapping()
    public Rdv create(@RequestBody Rdv rdv, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String idDoc = jwt.getClaimAsString("sub");

        rdv.setDoctor_id(idDoc);



        return  rdvService.Creer(rdv);
    }
    @PutMapping("/{id}")
    public Rdv update(@RequestBody Rdv rdv, @PathVariable Long id )
    {
        return rdvService.Modifier(rdv,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long  id){
        return rdvService.Supprimer(id);

    }
}
