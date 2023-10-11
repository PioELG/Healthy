package com.example.Health.service;

import com.example.Health.model.Antecedent;
import com.example.Health.model.Pathologie;

import java.util.List;

public interface AntecedentService {
    Antecedent Creer(Antecedent antecedent);
    List<Antecedent> Lire();
}
