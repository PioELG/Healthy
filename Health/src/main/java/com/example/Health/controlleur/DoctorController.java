package com.example.Health.controlleur;

import com.example.Health.model.Doctor;
import com.example.Health.model.Malade;
import com.example.Health.repository.MaladeRepository;
import com.example.Health.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/doctor")
@AllArgsConstructor
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    MaladeRepository maladeRepository;
    @GetMapping
    public Doctor read(Authentication authentication)
    {
        Malade malade= new Malade();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String id = jwt.getClaimAsString("sub");

        malade=maladeRepository.findByPat(id);

        return doctorService.LireD(malade.getTraitant());
    }
}
