package com.uniandes.tesis.demo.dataaccess.repository;

import com.uniandes.tesis.demo.dataaccess.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VueloRepository extends JpaRepository<Vuelo,Long> {
    Optional<Vuelo> findById(Long id);

    List<Vuelo> findAll();
}
