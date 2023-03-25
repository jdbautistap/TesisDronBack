package com.uniandes.tesis.demo.service;

import com.uniandes.tesis.demo.dataaccess.model.Vuelo;
import com.uniandes.tesis.demo.dataaccess.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    public Vuelo createVuelo(Vuelo vuelo){
        return vueloRepository.save(vuelo);
    }
}
