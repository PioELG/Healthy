package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Medecin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medecin {
    @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String age;
    @Column
    private String email;
    @Column
    private String specialite;





}