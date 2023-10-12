package com.example.Health.service;

import com.example.Health.model.ModeleMedicament;

import java.util.List;

public interface ModeleMedicamentService {
    ModeleMedicament Creer(ModeleMedicament modeleMedicament);
    List<ModeleMedicament> Lire();

    String Supprimer(Long id);


}
