package com.example.Health.controlleur;

import com.example.Health.model.Posologie;
import com.example.Health.model.Symptomes;
import com.example.Health.service.PosologieService;
import com.example.Health.service.SymptomesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
public class PosologieController {
    @Autowired
    private  final PosologieService posologieService;
    @GetMapping
    public List<Posologie> read()
    {
        return posologieService.Lire();
    }
    @PostMapping
    public Posologie create(@RequestBody Posologie posologie)
    {
        return posologieService.Creer(posologie);
    }
    @PutMapping("/{id}")
    public Posologie update(@RequestBody Posologie posologie, @PathVariable Long id )
    {
        return posologieService.Modifier(posologie,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return posologieService.Supprimer(id);

    }
}
