package com.example.Health.controlleur;

import com.example.Health.model.ModeleMedicament;
import com.example.Health.service.ModeleMedicamentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ModeleMedicament> read(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return modeleMedicamentService.Lire(pageable);


    }
    @GetMapping("/all")
    public List<ModeleMedicament> LireAll()
    {
        return modeleMedicamentService.LireAll();
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id)
    {
        return modeleMedicamentService.Supprimer(id);
    }
}
