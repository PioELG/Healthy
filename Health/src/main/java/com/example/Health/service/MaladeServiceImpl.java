package com.example.Health.service;

import com.example.Health.model.Constante;
import com.example.Health.model.Malade;
import com.example.Health.repository.MaladeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaladeServiceImpl implements MaladeService{
    @Autowired
    MaladeRepository maladeRepository;
    @Override
    public Malade Creer(Malade malade) {
        return maladeRepository.save(malade);
    }

    @Override
    public List<Malade> Lire() {
        return maladeRepository.findAll();
    }

    @Override
    public Malade Modifier(Malade malade, String id) {
         return maladeRepository.findById(id).map(m->{

            m.setStatut(malade.getStatut());
            m.setPathologie(malade.getPathologie());
             m.setTraitant(malade.getTraitant());

            return maladeRepository.save(m);

        }).orElseThrow(()-> new RuntimeException("malade non trouvé"));
    }

    @Override
    public Malade ModifierFT(Malade malade, String id) {
        return maladeRepository.findById(id).map(m->{

            m.setStatut(malade.getStatut());


            return maladeRepository.save(m);

        }).orElseThrow(()-> new RuntimeException("malade non trouvé"));
    }

    @Override
    public String Supprimer(String id) {
         maladeRepository.deleteById(id);
         return "malade bien supprimé";
    }

    @Override
    public List<Malade> LireNT( ) {
        return maladeRepository.NonTraite("Non traité");
    }

    @Override
    public List<Malade> LireST( String doc) {
        return maladeRepository.SousT("Sous traitement",doc);
    }

    @Override
    public List<Malade> LireParPat(String patient_id) {
        return maladeRepository.findByPatient_id(patient_id);
    }


    @Override
    public boolean exist(String id) {
        return maladeRepository.existsById(id);
    }

    public Malade ModifierS(Malade malade, String id) {
        return maladeRepository.findById(id).map(m -> {
            m.setStatut(malade.getStatut());



            return maladeRepository.save(m);

        }).orElseThrow(() -> new RuntimeException("constantes non trouvé"));
    }
}
