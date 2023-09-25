package com.example.Health.controlleur;

import com.example.Health.model.RendezVous;
import com.example.Health.model.Symptomes;
import com.example.Health.service.RendezVousService;
import com.example.Health.service.SymptomesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor

public class RendezVousController {
    @Autowired
    private  final RendezVousService rendezVousService;
    @GetMapping
    public List<RendezVous> read()
    {
        return rendezVousService.Lire();
    }
    //    @GetMapping("/{id}")
//    public List<Symptomes> readParPatient(@PathVariable Long id)
//    {
//
//        return symptomesService.LireParPatient(id);
//    }
    @PostMapping
    public RendezVous create(@RequestBody RendezVous rendezVous)
    {
        return rendezVousService.Creer(rendezVous);
    }
    @PutMapping("/{id}")
    public RendezVous update(@RequestBody RendezVous rendezVous, @PathVariable Long id )
    {
        return rendezVousService.Modifier(rendezVous,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return rendezVousService.Supprimer(id);

    }
}
