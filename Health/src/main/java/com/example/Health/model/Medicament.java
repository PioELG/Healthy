package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Medicament")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Medicament {
      @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

      @Column
    private String nom;

    @Column
    private String patient_id;
    @Column
    private  String medecin_id;

    @Column
    private String Prescription;

}
