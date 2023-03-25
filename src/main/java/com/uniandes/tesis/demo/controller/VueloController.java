package com.uniandes.tesis.demo.controller;

import com.uniandes.tesis.demo.dataaccess.model.Vuelo;
import com.uniandes.tesis.demo.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/vuelo")
public class VueloController {

    @Autowired
    private VueloService vueloService;


    @PostMapping
    private ResponseEntity<Vuelo> saveVuelo(@RequestBody Vuelo vuelo){
        Vuelo tmp =  vueloService.createVuelo(vuelo);
        try{
            return ResponseEntity.created(new URI("/api/vuelo"+tmp.getId())).body(tmp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
