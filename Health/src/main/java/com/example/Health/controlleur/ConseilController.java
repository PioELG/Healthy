package com.example.Health.controlleur;

import com.example.Health.model.Conseil;
import com.example.Health.model.Doctor;
import com.example.Health.model.Patient;
import com.example.Health.model.Symptomes;
import com.example.Health.service.ConseilService;
import com.example.Health.service.DoctorService;
import com.example.Health.service.PatientService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/conseils")

@AllArgsConstructor

public class ConseilController {


    @Autowired
    DoctorService doctorService;
    @Autowired
    private  final ConseilService conseilService;

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
