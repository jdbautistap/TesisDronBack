package com.uniandes.tesis.demo.service;


import com.uniandes.tesis.demo.dataaccess.model.Usuario;
import com.uniandes.tesis.demo.dataaccess.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario chekUsuarioEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
}
