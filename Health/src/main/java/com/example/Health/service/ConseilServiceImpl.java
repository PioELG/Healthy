package com.example.Health.service;

import com.example.Health.model.Conseil;
import com.example.Health.repository.ConseilRepository;
import com.example.Health.repository.SymptomesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConseilServiceImpl implements ConseilService{
    @Autowired
    private ConseilRepository conseilRepository;
    @Override
    public Conseil Creer(Conseil conseil) {
        return conseilRepository.save(conseil);
    }

    @Override
    public List<Conseil> Lire() {
        return conseilRepository.findAll();
    }

    @Override
    public Conseil Modifier(Conseil conseil, Long id) {
        return conseilRepository.findById(id).map(c->{
            c.setContenu(conseil.getContenu());

            return conseilRepository.save(c);

        }).orElseThrow(()-> new RuntimeException("conseil non trouvé"));

    }

    @Override
    public String Supprimer(Long id) {
        conseilRepository.deleteById(id);
        return "Supprimé avec succès";
    }
}
