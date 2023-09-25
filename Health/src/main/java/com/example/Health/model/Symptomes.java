package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Symptomes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Symptomes {
        @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
        @Column
    private String nom;
    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;


}
