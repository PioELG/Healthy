package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Prescription")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
       @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
       @Column
    private Date date;
 @ManyToOne
 @JoinColumn(name="patient_id")
 private Patient patient;

    @ManyToOne
    @JoinColumn(name="medecin_id")
    private Medecin medecin;

    @OneToMany(mappedBy = "prescription")
    private List<Medicament> medicamentList;



}
