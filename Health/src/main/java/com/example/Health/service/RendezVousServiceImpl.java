package com.example.Health.service;

import com.example.Health.model.RendezVous;
import com.example.Health.repository.RendezVousRepository;
import com.example.Health.repository.SymptomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RendezVousServiceImpl implements RendezVousService{
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Override
    public RendezVous Creer(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public List<RendezVous> Lire() {
        return rendezVousRepository.findAll();
    }

    @Override
    public RendezVous Modifier(RendezVous rendezVous, Long id) {
        return rendezVousRepository.findById(id).map(r->{
            r.setDate(rendezVous.getDate());
            r.setHeure(rendezVous.getHeure());

            return rendezVousRepository.save(r);

        }).orElseThrow(()-> new RuntimeException("RendezVous non trouvé"));
    }

    @Override
    public String Supprimer(Long id) {
        rendezVousRepository.deleteById(id);
        return "RendezVous bien supprimé";
    }
}
