package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="RendezVous")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RendezVous {
    @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date date;
    private String heure;

    @ManyToOne
    @JoinColumn(name="medecin_id")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

}
