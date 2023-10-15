package com.example.Health.service;

import com.example.Health.model.ModeleMedicament;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModeleMedicamentService {
    ModeleMedicament Creer(ModeleMedicament modeleMedicament);
    Page <ModeleMedicament> Lire(Pageable pageable);
    List<ModeleMedicament> LireAll();



    String Supprimer(Long id);


}
