package com.example.Health.controlleur;

import com.example.Health.model.Conseil;
import com.example.Health.model.Patient;
import com.example.Health.model.Symptomes;
import com.example.Health.service.ConseilService;
import com.example.Health.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/conseils")

@AllArgsConstructor

public class ConseilController {
    @Autowired
    private  final ConseilService conseilService;

    @GetMapping()
    public List<Conseil> read(@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        return conseilService.LireP(id);
    }
    @PostMapping
    public Conseil create(@RequestBody Conseil conseil,@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        conseil.setMedecin_id(id);

        return conseilService.Creer(conseil);
    }
    @PutMapping("/{id}")
    public Conseil update(@RequestBody Conseil conseil, @PathVariable Long id )
    {
        return conseilService.Modifier(conseil,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return conseilService.Supprimer(id);

    }
//    @GetMapping("/{id}")
//    public List<Symptomes> readParPatient(@PathVariable Long id)
//    {
//
//        return symptomesService.LireParPatient(id);
//    }
}
