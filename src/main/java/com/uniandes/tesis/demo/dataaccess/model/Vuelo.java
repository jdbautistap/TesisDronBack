package com.uniandes.tesis.demo.dataaccess.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vuelo")
@Data
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String location;
    private Date date;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "vuelo")
    private List<TakenPhoto> takenPhotos;
}
