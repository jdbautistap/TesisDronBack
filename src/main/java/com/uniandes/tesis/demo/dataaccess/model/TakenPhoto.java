package com.uniandes.tesis.demo.dataaccess.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TakenPhoto")
@Data
public class TakenPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Vuelo vuelo;
    private String ruta;
    private String nombre;
    @Lob
    @Column(name = "data")
    private byte[] data;
}
