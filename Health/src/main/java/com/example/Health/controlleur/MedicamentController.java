package com.example.Health.controlleur;

import com.example.Health.model.Malade;
import com.example.Health.model.Medicament;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.repository.MedicamentRepository;
import com.example.Health.repository.PosologieRepository;
import com.example.Health.service.ConstantesService;
import com.example.Health.service.EmailService;
import com.example.Health.service.MedicamentService;
import com.example.Health.service.PosologieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping(path="/api/medicament")
@AllArgsConstructor

public class MedicamentController {
    @Autowired
    private  final MedicamentService medicamentService;
    @Autowired
    private final EmailService emailService;

    @Autowired
    private final MaladeRepository maladeRepository;

    @Autowired
    private  final MedicamentRepository medicamentRepository;

    @Autowired
    private PosologieService posologieService;


    @GetMapping
    public List<Medicament> read(Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");

        return medicamentService.LireParPat(id,"Oui");
    }
    @GetMapping("/medecin")
    public List<Medicament> readMede(Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");

        return medicamentService.LireMed(id);
    }
    @GetMapping("/all/{id}")
    public List<Medicament> readAll(@PathVariable String id)
    {

        return medicamentService.LirePat(id);
    }
    @GetMapping("/unique/{id}")
    public Medicament readUnique(@PathVariable Long id)
    {

        return medicamentService.Unique(id);
    }
    @GetMapping("/{idP}")
    public List<Medicament> readP(@PathVariable String idP,Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        return medicamentService.LireParMed(id,idP,"Oui");
    }
    @PostMapping
    public Medicament create(@RequestBody Medicament medicament, Authentication  authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        medicament.setMedecin_id(id);
        medicament.setPrescription("Oui");
        medicament.setDatePresc(LocalDate.now());
        return medicamentService.Creer(medicament);
    }
    @PutMapping("/{id}")
    public Medicament update(@RequestBody Medicament medicament, @PathVariable Long id )
    {
        return medicamentService.Modifier(medicament,id);
    }
    @PutMapping("statut/{id}")
    public Medicament updateStatut(@RequestBody Medicament medicament, @PathVariable Long id )
    {
        return medicamentService.ModifierStatut(medicament,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return medicamentService.Supprimer(id);

    }
  //  @Scheduled(cron="0 */2 * * * *")
   /* public void CronTest()  throws jakarta.mail.MessagingException
    {
        int i;

        for(i=0; i<maladeRepository.SousTMail("Sous traitement").size();i++)
        {


            Malade malade= new Malade();
            malade =maladeRepository.SousTMail("Sous traitement").get(i);


            emailService.sendEmail(malade.getNom(),malade.getPrenom(),malade.getEmail(),medicamentRepository.findByPatient(malade.getId(),"Oui"),  posologieService.Lire());

        }

    }*/

}
