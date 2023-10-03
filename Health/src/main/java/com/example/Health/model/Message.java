package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Constante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        @Column
        private String contenu;
        @Column
        private String medecin_id;
        @Column
        private String patient_id;
}
