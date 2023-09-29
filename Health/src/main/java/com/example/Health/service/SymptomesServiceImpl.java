package com.example.Health.service;

import com.example.Health.model.Symptomes;
import com.example.Health.repository.SymptomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SymptomesServiceImpl implements SymptomesService{
    @Autowired
    private SymptomesRepository symptomesRepository;
    @Override
    public Symptomes Creer(Symptomes symptomes) {
        return symptomesRepository.save(symptomes);
    }

    @Override
    public List<Symptomes> Lire() {
        return symptomesRepository.findAll();
    }

    @Override
    public Symptomes Modifier(Symptomes symptomes, Long id) {

        return symptomesRepository.findById(id).map(s->{
            s.setNom(symptomes.getNom());


            return symptomesRepository.save(s);

        }).orElseThrow(()-> new RuntimeException("symptomes non trouvé"));
    }

    @Override
    public String Supprimer(Long id) {
        symptomesRepository.deleteById(id);
        return "Symptomes bien supprimé";
    }

    @Override
    public List<Symptomes> LireParPatient(String patient_id) {
        return symptomesRepository.findByPatient_id(patient_id);
    }
}
