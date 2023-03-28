package com.uniandes.tesis.demo.dataaccess.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Boolean publico;
    @ManyToOne
    @JoinColumn
    private Usuario usuario;
    @OneToMany(mappedBy = "vuelo")
    private List<TakenPhoto> takenPhotos;
}
