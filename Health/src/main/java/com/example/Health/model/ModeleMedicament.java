package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ModeleMedicament")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModeleMedicament {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;

}
