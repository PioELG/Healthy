package com.example.Health.service;

import com.example.Health.model.Medecin;
import com.example.Health.repository.MedecinRepository;
import com.example.Health.repository.MedicamentRepository;
import com.example.Health.repository.SymptomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedecinServiceImpl implements MedecinService {
    @Autowired
    private MedecinRepository medecinRepository;
    @Override
    public Medecin Creer(Medecin medecin) {
        return null;
    }

    @Override
    public List<Medecin> Lire() {
        return null;
    }

    @Override
    public Medecin Modifier(Medecin medecin, Long id) {
        return medecinRepository.findById(id).map(m->{
            m.setNom(medecin.getNom());
            m.setPrenom(medecin.getPrenom());
            m.setAge(medecin.getAge());
            m.setEmail(medecin.getEmail());
            m.setSpecialite(medecin.getSpecialite());

            return medecinRepository.save(m);

        }).orElseThrow(()-> new RuntimeException("medecin non trouvé"));
    }

    @Override
    public String Supprimer(Long id) {
        return null;
    }
}
