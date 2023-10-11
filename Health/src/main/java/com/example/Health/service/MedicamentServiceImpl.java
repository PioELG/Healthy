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
    public List<Medicament> LireParPat(String patient_id, String statut) {
        return medicamentRepository.findByPatient(patient_id,statut);
    }

    @Override
    public List<Medicament> LireParMed(String medecin_id,String patient_id,String statut) {

        return medicamentRepository.findByMedecin(medecin_id,patient_id,statut);
    }

    @Override
    public List<Medicament> LirePat(String patient_id) {
        return medicamentRepository.findByPatientId(patient_id);
    }

    @Override
    public List<Medicament> LireMed(String medecin_id) {
        return medicamentRepository.findByMedecinId(medecin_id);
    }


    @Override
    public Medicament Modifier(Medicament medicament, Long id) {
        return medicamentRepository.findById(id).map(m->{
            if(medicament.getNom()!=null)
            {
                m.setNom(medicament.getNom());
            }
            if(medicament.getDuree()!=null)
            {
                m.setDuree(medicament.getDuree());
            }
            return medicamentRepository.save(m);

        }).orElseThrow(()-> new RuntimeException("medicament non trouvé"));
    }

    @Override
    public Medicament ModifierStatut(Medicament medicament, Long id) {
        return medicamentRepository.findById(id).map(m->{
            m.setPrescription(medicament.getPrescription());

            return medicamentRepository.save(m);

        }).orElseThrow(()-> new RuntimeException("medicament non trouvé"));
    }

    @Override
    public String Supprimer(Long id) {
        medicamentRepository.deleteById(id);
        return "medicament supprimé avec succès";
    }

    @Override
    public Medicament Unique(Long id) {
        return medicamentRepository.findByUnique(id);
    }
}
