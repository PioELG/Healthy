package com.example.Health.service;

import com.example.Health.model.ModeleMedicament;
import com.example.Health.model.Pathologie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PathologieService {
Pathologie Creer(Pathologie pathologie);
    Page<Pathologie> Lire(Pageable pageable);

    List<Pathologie> LireAll();
    String Supprimer(Long id);
}
