package com.example.Health.service;

import com.example.Health.model.Constante;
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
    public Constante Creer(Constante constante) {
        return constantesRepository.save(constante);
    }

    @Override
    public List<Constante> Lire() {
        return constantesRepository.findAll();
    }

    @Override
    public Constante Modifier(Constante constante, Long id) {
        return constantesRepository.findById(id).map(c->{
            c.setPoids(constante.getPoids());
            c.setFreqCar(constante.getFreqCar());
            c.setFreqRes(constante.getFreqRes());
            c.setTemperature(constante.getTemperature());
            c.setPressionArterielle(constante.getPressionArterielle());

            return constantesRepository.save(c);

        }).orElseThrow(()-> new RuntimeException("constantes non trouvé"));

    }

    @Override
    public String Supprimer(Long id) {
        constantesRepository.deleteById(id);
        return "Constantes bien supprimée";
    }

    @Override
    public List<Constante> LireP(String user_id) {
        return constantesRepository.findByPatient_id(user_id);
    }


}
