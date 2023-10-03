package com.example.Health.controlleur;

import com.example.Health.model.Medicament;
import com.example.Health.service.ConstantesService;
import com.example.Health.service.MedicamentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/api/medicament")
@AllArgsConstructor

public class MedicamentController {
    @Autowired
    private  final MedicamentService medicamentService;
    @GetMapping
    public List<Medicament> read(Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");

        return medicamentService.LireParPat(id);
    }
    @GetMapping("/{idP}")
    public List<Medicament> readP(@PathVariable String idP,Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        return medicamentService.LireParMed(id,idP);
    }
    @PostMapping
    public Medicament create(@RequestBody Medicament medicament, Authentication  authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        medicament.setMedecin_id(id);
        return medicamentService.Creer(medicament);
    }
    @PutMapping("/{id}")
    public Medicament update(@RequestBody Medicament medicament, @PathVariable Long id )
    {
        return medicamentService.Modifier(medicament,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return medicamentService.Supprimer(id);

    }

}
