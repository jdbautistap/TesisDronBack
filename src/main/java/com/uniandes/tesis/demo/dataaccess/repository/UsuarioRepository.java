package com.uniandes.tesis.demo.dataaccess.repository;

import com.uniandes.tesis.demo.dataaccess.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);
}
