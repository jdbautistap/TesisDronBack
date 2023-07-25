package com.uniandes.tesis.demo.dataaccess.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vuelo")
@Data
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String MissionCode;
    private Double locationinitLat;
    private Double locationinitLng;
    private Date date;
    private Double altitude;
    private String type;
    private String heading;
    private String finishing;
    private int numberWaypoints;
    private int radius;
    private double speed;
    private double width;
    private double height;
    private double angle;
    @Lob
    private String waypointsList;
    private String duration;
    private Double locationFinishLat;
    private Double locationFinishLng;
    private Date finishdate;
    @Lob
    private String coordinates;
    @ManyToOne
    @JoinColumn
    private Usuario usuario;
    @JsonIgnore
    @OneToMany(mappedBy = "vuelo")
    private List<TakenPhoto> takenPhotos;
}
