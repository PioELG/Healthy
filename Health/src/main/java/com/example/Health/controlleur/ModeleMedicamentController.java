package com.example.Health.controlleur;

import com.example.Health.model.ModeleMedicament;
import com.example.Health.service.ModeleMedicamentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/modele")
@AllArgsConstructor
public class ModeleMedicamentController {
    @Autowired
    private ModeleMedicamentService modeleMedicamentService;

    @PostMapping
    public ModeleMedicament create(@RequestBody ModeleMedicament modeleMedicament)
    {
        return  modeleMedicamentService.Creer(modeleMedicament);

    }
    @GetMapping
    public List<ModeleMedicament> read()
    {
        return modeleMedicamentService.Lire();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id)
    {
        return modeleMedicamentService.Supprimer(id);
    }
}
