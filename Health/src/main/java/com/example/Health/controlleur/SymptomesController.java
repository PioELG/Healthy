package com.example.Health.controlleur;


import com.example.Health.model.Symptomes;
import com.example.Health.service.SymptomesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/symptomes")
@AllArgsConstructor
public class SymptomesController {
    @Autowired
    private  final SymptomesService symptomesService;
    @GetMapping
    public List<Symptomes> read(@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
       return symptomesService.LireParPatient(id);
    }

    @GetMapping("/{id}")
    public List<Symptomes> readParPatient(@PathVariable String id)
    {

        return symptomesService.LireParPatient(id);
    }
    @PostMapping
    public Symptomes create(@RequestBody Symptomes symptomes,@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        symptomes.setPatient_id(id);
        return symptomesService.Creer(symptomes);
    }
    @PutMapping("/{id}")
    public Symptomes update(@RequestBody Symptomes symptomes, @PathVariable Long id )
    {
        return symptomesService.Modifier(symptomes,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return symptomesService.Supprimer(id);

    }
}
