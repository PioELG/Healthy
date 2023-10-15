package com.example.Health.service;

import com.example.Health.model.ModeleMedicament;
import com.example.Health.repository.ModeleMedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
@Service
public class ModeleServiceImpl implements ModeleMedicamentService{

    @Autowired
    private ModeleMedicamentRepository modeleMedicamentRepository;
    @Override
    public ModeleMedicament Creer(ModeleMedicament modeleMedicament) {
        return modeleMedicamentRepository.save(modeleMedicament);
    }

    @Override
    public Page <ModeleMedicament> Lire(Pageable pageable) {
        return modeleMedicamentRepository.OrderB(pageable);
    }

    @Override
    public List<ModeleMedicament> LireAll() {
        return modeleMedicamentRepository.findAll();
    }

    @Override
    public String Supprimer(Long id) {
         modeleMedicamentRepository.deleteById(id);
         return "Modele bien supprimé";
    }


}
