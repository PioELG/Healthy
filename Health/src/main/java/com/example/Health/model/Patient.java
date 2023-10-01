package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
      @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
       @Column
    private String nom;
        @Column
    private String prenom;
       @Column
    private String age;
       @Column
    private String email;
        @Column
    private String statut;





    @ManyToOne
    @JoinColumn(name="medecin_id")
    private Medecin medecin;








}
