package com.example.Health.controlleur;

import com.example.Health.model.Patient;
import com.example.Health.model.Symptomes;
import com.example.Health.service.PatientService;
import com.example.Health.service.SymptomesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/api/patient")
@AllArgsConstructor

public class PatientController {
    @Autowired
    private  final PatientService patientService;
    @GetMapping
    public List<Patient> read()
    {
        return patientService.Lire();
    }
    @PostMapping
    public Patient create(@RequestBody Patient patient)
    {
        return patientService.Creer(patient);
    }
    @PutMapping("/{id}")
    public Patient update(@RequestBody Patient patient, @PathVariable Long id )
    {
        return patientService.Modifier(patient,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return patientService.Supprimer(id);

    }
}
