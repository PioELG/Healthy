package com.example.Health.service;

import com.example.Health.model.ModeleMedicament;
import com.example.Health.repository.ModeleMedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ModeleMedicament> Lire() {
        return modeleMedicamentRepository.OrderB();
    }

    @Override
    public String Supprimer(Long id) {
         modeleMedicamentRepository.deleteById(id);
         return "Modele bien supprimé";
    }


}
