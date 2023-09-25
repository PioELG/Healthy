package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Constantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Constantes {
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

    @OneToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

}
