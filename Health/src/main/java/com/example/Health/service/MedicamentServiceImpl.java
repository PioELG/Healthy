package com.example.Health.service;

import com.example.Health.model.Medicament;
import com.example.Health.repository.MedecinRepository;
import com.example.Health.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicamentServiceImpl implements MedicamentService{
    @Autowired
    private MedicamentRepository medicamentRepository;

    @Override
    public Medicament Creer(Medicament medicament) {
        return medicamentRepository.save(medicament);
    }

    @Override
    public List<Medicament> Lire() {
        return medicamentRepository.findAll();
    }

    @Override
    public Medicament Modifier(Medicament medicament, Long id) {
        return medicamentRepository.findById(id).map(m->{
            m.setNom(medicament.getNom());

            return medicamentRepository.save(m);

        }).orElseThrow(()-> new RuntimeException("medicament non trouvé"));
    }

    @Override
    public String Supprimer(Long id) {
        medicamentRepository.deleteById(id);
        return "medicament supprimé avec succès";
    }
}
