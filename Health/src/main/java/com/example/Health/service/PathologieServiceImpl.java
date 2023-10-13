package com.example.Health.service;

import com.example.Health.model.Pathologie;
import com.example.Health.repository.PathologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PathologieServiceImpl implements PathologieService{
    @Autowired
    PathologieRepository pathologieRepository;
    @Override
    public Pathologie Creer(Pathologie pathologie) {
        return pathologieRepository.save(pathologie);
    }

    @Override
    public List<Pathologie> Lire() {
        return pathologieRepository.OrderB();
    }

    @Override
    public String Supprimer(Long id) {
        pathologieRepository.deleteById(id);
        return "Pathologie bien supprimée";
    }
}
