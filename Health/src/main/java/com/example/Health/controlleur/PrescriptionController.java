package com.example.Health.controlleur;

import com.example.Health.model.Prescription;
import com.example.Health.model.Symptomes;
import com.example.Health.service.PrescriptionService;
import com.example.Health.service.SymptomesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/api/prescription")
@AllArgsConstructor
public class PrescriptionController {
    @Autowired
    private  final PrescriptionService prescriptionService;
    @GetMapping
    public List<Prescription> read()
    {



        return prescriptionService.Lire();
    }

    @PostMapping
    public Prescription create(@RequestBody Prescription prescription, Authentication authentication)
    {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");
        prescription.setDoctor_id(id);
        return prescriptionService.Creer(prescription);
    }
//    @PutMapping("/{id}")
//    public Prescription update(@RequestBody Prescription prescription, @PathVariable Long id )
//    {
//        return prescriptionService.Modifier(prescription,id);
//    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return prescriptionService.Supprimer(id);

    }
}
