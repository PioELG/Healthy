package com.example.Health.service;

import com.example.Health.model.Constantes;
import com.example.Health.repository.ConstantesRepository;
import com.example.Health.repository.SymptomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConstantesServiceImpl implements ConstantesService{
    @Autowired
    private ConstantesRepository constantesRepository;
    @Override
    public Constantes Creer(Constantes constantes) {
        return constantesRepository.save(constantes);
    }

    @Override
    public List<Constantes> Lire() {
        return constantesRepository.findAll();
    }

    @Override
    public Constantes Modifier(Constantes constantes, Long id) {
        return constantesRepository.findById(id).map(c->{
            c.setPoids(constantes.getPoids());
            c.setFreqCar(constantes.getFreqCar());
            c.setFreqRes(constantes.getFreqRes());
            c.setTemperature(constantes.getTemperature());
            c.setPressionArterielle(constantes.getPressionArterielle());

            return constantesRepository.save(c);

        }).orElseThrow(()-> new RuntimeException("constantes non trouvé"));

    }

    @Override
    public String Supprimer(Long id) {
        constantesRepository.deleteById(id);
        return "Constantes bien supprimée";
    }
}
