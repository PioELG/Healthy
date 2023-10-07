package com.example.Health.service;

import com.example.Health.model.Rdv;
import com.example.Health.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RdvServiceImpl implements RdvService {
    @Autowired
    private RdvRepository rdvRepository;
    @Override
    public Rdv Creer(Rdv rdv) {

        return rdvRepository.save(rdv);
    }

    @Override
    public List<Rdv> Lire() {
        return rdvRepository.findAll();
    }

    @Override
    public List<Rdv> LireP(String malade_id) {
        return rdvRepository.findByMalade_id(malade_id);
    }

    @Override
    public List<Rdv> LireM(String doctor_id) {
        return rdvRepository.findByDoctor_id(doctor_id);
    }

    @Override
    public Rdv Modifier(Rdv rdv, Long id) {
        return rdvRepository.findById(id).map(r->{
            r.setDate(rdv.getDate());
            r.setHeure(rdv.getHeure());


            return rdvRepository.save(r);

        }).orElseThrow(()-> new RuntimeException("RendezVous non trouvé"));
    }



    @Override
    public String Supprimer(Long id) {
        rdvRepository.deleteById(id);
        return "Rdv bien supprimé";
    }

    @Override
    public void SupprimerMalade(String malade_id) {
        rdvRepository.deleteByMalade_id(malade_id);

    }
}
