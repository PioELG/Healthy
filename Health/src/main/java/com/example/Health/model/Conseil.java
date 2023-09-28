package com.example.Health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Conseil")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conseil {
   @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String contenu;

//    @ManyToOne
//    @JoinColumn(name="medecin_id")
//    private Medecin medecin;
 @Column
 private String medecin_id;
}
