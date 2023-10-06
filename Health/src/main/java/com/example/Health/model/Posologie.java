package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="Posologie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Posologie {
    @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
       @Column
    private int quantite;
       @Column
    private String heurePrise;
    @Column
    private String unite;
    @Column
    private Long medicament_id;
    @Column
    private String statut;


}
