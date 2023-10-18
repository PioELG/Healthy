package com.example.Health.controlleur;

import com.example.Health.model.Constante;
import com.example.Health.model.Malade;
import com.example.Health.model.Patient;
import com.example.Health.model.Symptomes;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.service.ConstantesService;
import com.example.Health.service.DoctorService;
import com.example.Health.service.MaladeService;
import com.example.Health.service.PatientService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/constantes")
@AllArgsConstructor

public class ConstantesController {
    @Autowired
    MaladeService maladeService;
    @Autowired
    private  final ConstantesService constantesService;

    @Autowired
    private MaladeRepository maladeRepository;
    @GetMapping
    public Constante read(@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication)
    {
        Malade malade= new Malade();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
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


        return constantesService.LireP(id).get(0);
    }

  @GetMapping("/{id}")
   public List<Constante> readParPatient(@PathVariable String id)
    {

        return constantesService.LireP(id);
    }

    public List<Constante> readAllParPatient(@PathVariable String id)
    {

        return constantesService.LireAll(id);
    }
    @PostMapping
    public Constante create(@RequestBody Constante constante,@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");

        constante.setPatient_id(id);
        constante.setDateModif(LocalDate.now());

        return constantesService.Creer(constante);
    }
    @PutMapping("/{id}")
    public Constante update(@RequestBody Constante constante, @PathVariable Long id )
    {
        return constantesService.Modifier(constante,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return constantesService.Supprimer(id);

    }
    @DeleteMapping("malade/{idM}")
    public void deleteMalade ( @PathVariable String idM){
        constantesService.SupprimerMalade(idM);

    }
}
