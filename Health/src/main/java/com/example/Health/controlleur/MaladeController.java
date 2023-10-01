package com.example.Health.controlleur;

import com.example.Health.model.Malade;
import com.example.Health.model.Patient;
import com.example.Health.service.MaladeService;
import com.example.Health.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/malade")
@AllArgsConstructor
public class MaladeController {
    @Autowired
    private  final MaladeService maladeService;
    @GetMapping("/{id}")
    public List<Malade> readParPatient(@RequestHeader(value = "Accept")String acceptHeader,@PathVariable String id)
   {

       return maladeService.LireParPat(id);
   }
    @GetMapping("/Nt")
    public List<Malade> readNt()
    {

        return maladeService.LireNT();
    }
    @GetMapping("/St")
    public List<Malade> readSt(Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        return maladeService.LireST(id);
    }
    @PostMapping
    public Malade create(@RequestBody Malade malade)
    {
        return maladeService.Creer(malade);
    }
    @PutMapping("/{id}")
    public Malade update(@RequestBody Malade malade, @PathVariable String  id )
    {
        return maladeService.Modifier(malade,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable String id){
        return maladeService.Supprimer(id);

    }
}
