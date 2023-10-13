package com.example.Health.service;

import com.example.Health.model.ModeleMedicament;
import com.example.Health.model.Pathologie;

import java.util.List;

public interface PathologieService {
Pathologie Creer(Pathologie pathologie);
    List<Pathologie> Lire();
    String Supprimer(Long id);
}
