package com.example.Health.controlleur;

import com.example.Health.model.Constantes;
import com.example.Health.model.Patient;
import com.example.Health.model.Symptomes;
import com.example.Health.service.ConstantesService;
import com.example.Health.service.PatientService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/api/constantes")
@AllArgsConstructor

public class ConstantesController {
    @Autowired
    private  final ConstantesService constantesService;
    @GetMapping
    public List<Constantes> read()
    {
        return constantesService.Lire();
    }
//    @GetMapping("/{id}")
//    public List<Symptomes> readParPatient(@PathVariable Long id)
//    {
//
//        return symptomesService.LireParPatient(id);
//    }
    @PostMapping
    public Constantes create(@RequestBody Constantes constantes)
    {
        return constantesService.Creer(constantes);
    }
    @PutMapping("/{id}")
    public Constantes update(@RequestBody Constantes constantes, @PathVariable Long id )
    {
        return constantesService.Modifier(constantes,id);
    }
    @DeleteMapping("/{id}")
    public String delete ( @PathVariable Long id){
        return constantesService.Supprimer(id);

    }
}
