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
        Doctor doctor=new Doctor();
        Jwt jwt = (Jwt) authentication.getPrincipal();

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
