package com.example.Health.controlleur;

import com.example.Health.model.Constantes;
import com.example.Health.model.Medicament;
import com.example.Health.service.ConstantesService;
import com.example.Health.service.MedicamentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor

public class MedicamentController {
    @Autowired
    private  final MedicamentService medicamentService;
    @GetMapping
    public List<Medicament> read()
    {
        return medicamentService.Lire();
    }
    @PostMapping
    public Medicament create(@RequestBody Medicament medicament)
    {
        return medicamentService.Creer(medicament);
    }
    @PutMapping("/{id}")
    public Medicament update(@RequestBody Medicament medicament, @PathVariable Long id )
    {
        return medicamentService.Modifier(medicament,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return medicamentService.Supprimer(id);

    }
}
