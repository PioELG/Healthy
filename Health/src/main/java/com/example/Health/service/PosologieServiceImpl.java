package com.example.Health.service;

import com.example.Health.model.Medicament;
import com.example.Health.model.Posologie;
import com.example.Health.repository.PosologieRepository;
import com.example.Health.repository.SymptomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PosologieServiceImpl implements PosologieService{
    @Autowired
    private PosologieRepository posologieRepository;
    @Autowired
    MedicamentService medicamentService;
    @Override
    public Posologie Creer(Posologie posologie) {
        return posologieRepository.save(posologie);
    }

    @Override
    public List<Posologie> Lire() {
        return posologieRepository.findAll();
    }

    @Override
    public List<Posologie> LireP(Long id) {
        return posologieRepository.findByMedicament(id);
    }

    @Override
    public Posologie Modifier(Posologie posologie, Long id) {
        return posologieRepository.findById(id).map(p->{
            p.setHeurePrise(posologie.getHeurePrise());
            p.setQuantite(posologie.getQuantite());
            p.setUnite(posologie.getUnite());
            p.setStatut(posologie.getStatut());
            return posologieRepository.save(p);

        }).orElseThrow(()-> new RuntimeException("posologie non trouvé"));
    }

    @Override
    public String Supprimer(Long id) {
        posologieRepository.deleteById(id);
        return "posologie bien supprimée";
    }


}
