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
    private Time heurePrise;

    @ManyToOne
    @JoinColumn(name="medicament_id")
    private Medicament medicament;

}
