package com.example.Health.controlleur;
import com.example.Health.model.Medecin;
import com.example.Health.service.ConstantesService;
import com.example.Health.service.MedecinService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor

public class MedecinController {
    @Autowired
    private  final MedecinService medecinService;
    @GetMapping
    public List<Medecin> read()
    {
        return medecinService.Lire();
    }
    @PostMapping
    public Medecin create(@RequestBody Medecin medecin)
    {
        return medecinService.Creer(medecin);
    }
    @PutMapping("/{id}")
    public Medecin update(@RequestBody Medecin medecin, @PathVariable Long id )
    {
        return medecinService.Modifier(medecin,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return medecinService.Supprimer(id);

    }
}
