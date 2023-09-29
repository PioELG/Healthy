package com.example.Health.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="malade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Malade {
    @Id
    private String id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String email;
    @Column
    private String pathologie;
    @Column
   private String statut;
}
