package com.example.Health.controlleur;

import com.example.Health.model.Antecedent;
import com.example.Health.model.Pathologie;
import com.example.Health.service.AntecedentService;
import com.example.Health.service.PathologieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/pathologie")
@AllArgsConstructor
public class PathologieController {

    @Autowired
    private PathologieService pathologieService;

    @PostMapping
    public Pathologie create(@RequestBody Pathologie pathologie)
    {
        return  pathologieService.Creer(pathologie);

    }
    @GetMapping
    public List<Pathologie> read()
    {
        return pathologieService.Lire();
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id)
    {
        return pathologieService.Supprimer(id);
    }
}
