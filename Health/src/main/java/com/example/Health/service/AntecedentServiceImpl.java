package com.example.Health.service;

import com.example.Health.model.Antecedent;
import com.example.Health.repository.AntecedentRepository;
import com.example.Health.repository.PathologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AntecedentServiceImpl implements AntecedentService{
    @Autowired
    AntecedentRepository antecedentRepository;
    @Override
    public Antecedent Creer(Antecedent antecedent) {
        return antecedentRepository.save(antecedent);
    }

    @Override
    public List<Antecedent> Lire() {
        return antecedentRepository.OrderB();
    }
}
