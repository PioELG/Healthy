package com.example.Health.controlleur;

import com.example.Health.model.*;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.service.ConseilService;
import com.example.Health.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/conseils")

@AllArgsConstructor

public class ConseilController {


    @Autowired
    DoctorService doctorService;
    @Autowired
    private  final ConseilService conseilService;
    @Autowired
    private MaladeRepository maladeRepository;

    @GetMapping()
    public List<Conseil> read(@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");


        return conseilService.LireP(id);
    }

    @GetMapping("/doc")
    public List<Conseil> LireDoc(Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        Malade malade=new Malade();
        malade=maladeRepository.Single(id);
        String doc=malade.getTraitant();

        return conseilService.LireDoc(doc);
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
