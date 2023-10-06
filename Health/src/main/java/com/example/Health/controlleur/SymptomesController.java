package com.example.Health.controlleur;


import com.example.Health.model.Malade;
import com.example.Health.model.Symptomes;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.repository.MedicamentRepository;
import com.example.Health.repository.PosologieRepository;
import com.example.Health.service.EmailService;
import com.example.Health.service.MaladeService;
import com.example.Health.service.PosologieService;
import com.example.Health.service.SymptomesService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.mail;

@RestController
@RequestMapping(path="/api/symptomes")
@AllArgsConstructor
public class SymptomesController {
    @Autowired
    private  final SymptomesService symptomesService;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final MaladeRepository maladeRepository;

    @Autowired
    private  final PosologieRepository posologieRepository;

    @Autowired
    private PosologieService posologieService;

    @Autowired
    private  final MedicamentRepository medicamentRepository;

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
    public Symptomes create(@RequestBody Symptomes symptomes,@RequestHeader(value = "Accept")String acceptHeader, Authentication authentication)  {
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

    @Scheduled(cron="0 */10 * * * *")
    public void CronTest()  throws jakarta.mail.MessagingException
    {
        int i;

        for(i=0; i<maladeRepository.SousTMail("Sous traitement").size();i++)
        {


            Malade malade= new Malade();
            malade =maladeRepository.SousTMail("Sous traitement").get(i);

            medicamentRepository.findByPatient(malade.getId());
            emailService.sendEmail(malade.getNom(),malade.getPrenom(),malade.getEmail(),medicamentRepository.findByPatient(malade.getId()),  posologieService.Lire());

        }

    }
}
