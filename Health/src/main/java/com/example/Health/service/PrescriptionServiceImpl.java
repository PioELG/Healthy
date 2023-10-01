package com.example.Health.service;

import com.example.Health.model.Prescription;
import com.example.Health.repository.PrescriptionRepository;
import com.example.Health.repository.SymptomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrescriptionServiceImpl implements PrescriptionService{
//    @Autowired
//    private PrescriptionRepository prescriptionRepository;
//    @Override
//    public Prescription Creer(Prescription prescription) {
//        return prescriptionRepository.save(prescription);
//    }
//
//    @Override
//    public List<Prescription> Lire() {
//        return prescriptionRepository.findAll();
//    }
//
//    @Override
//    public Prescription Modifier(Prescription prescription, Long id) {
//        return prescriptionRepository.findById(id).map(p->{
//            p.setDate(p.getDate());
//
//
//            return prescriptionRepository.save(p);
//
//        }).orElseThrow(()-> new RuntimeException("prescription non trouvé"));
//    }
//
//    @Override
//    public String Supprimer(Long id) {
//         prescriptionRepository.deleteById(id);
//         return "prescription bien supprimé";
//    }
}
