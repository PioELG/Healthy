package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="Constante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Constante {
   @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
   @Column
    private String poids;
   @Column
    private String pressionArterielle;
    @Column
    private String freqCar;
   @Column
    private String freqRes;
   @Column
    private String temperature;

    @Column
    private String patient_id;
    @Column
    private LocalDate dateModif;

}
