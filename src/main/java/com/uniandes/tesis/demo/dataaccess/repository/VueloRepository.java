package com.uniandes.tesis.demo.dataaccess.repository;

import com.uniandes.tesis.demo.dataaccess.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo,Long> {

}
