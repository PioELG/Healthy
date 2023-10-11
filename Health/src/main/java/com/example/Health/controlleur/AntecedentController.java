package com.example.Health.controlleur;

import com.example.Health.model.Antecedent;
import com.example.Health.model.ModeleMedicament;
import com.example.Health.service.AntecedentService;
import com.example.Health.service.ModeleMedicamentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/antecedent")
@AllArgsConstructor

public class AntecedentController {

    @Autowired
    private AntecedentService antecedentService;

    @PostMapping
    public Antecedent create(@RequestBody Antecedent antecedent)
    {
        return  antecedentService.Creer(antecedent);

    }
    @GetMapping
    public List<Antecedent> read()
    {
        return antecedentService.Lire();
    }
}
