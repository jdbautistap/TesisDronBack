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



}
