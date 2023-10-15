package com.example.Health.controlleur;

import com.example.Health.model.Antecedent;
import com.example.Health.model.Pathologie;
import com.example.Health.service.AntecedentService;
import com.example.Health.service.PathologieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Pathologie> read(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return pathologieService.Lire(pageable);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id)
    {
        return pathologieService.Supprimer(id);
    }

    @GetMapping("/all")
    public List<Pathologie> readAll()
    {
        return pathologieService.LireAll();
    }
}
