package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Rdv")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rdv {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        @Column
        private String date;
        @Column
        private String heure;
        @Column
        private String malade_id;
        @Column
        private String doctor_id;

    }


