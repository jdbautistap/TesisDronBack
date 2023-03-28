package com.uniandes.tesis.demo.controller;

import com.uniandes.tesis.demo.dataaccess.model.Usuario;
import com.uniandes.tesis.demo.dataaccess.model.Vuelo;
import com.uniandes.tesis.demo.service.UsuarioService;
import com.uniandes.tesis.demo.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/usuario/{id}/vuelo")
public class VueloController {

    @Autowired
    private VueloService vueloService;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    private ResponseEntity<Object> saveVuelo(@PathVariable Long id, @RequestBody Vuelo vuelo) {
        if (usuarioService.checkUsuarioId(id).isPresent()){
            Usuario usuario=usuarioService.checkUsuarioId(id).get();
            vuelo.setUsuario(usuario);
            Vuelo tmp =  vueloService.createVuelo(vuelo);
            //System.out.println(vuelo);
            return new ResponseEntity<>(tmp,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("No existe un usuario con este Id "+id,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    private ResponseEntity<Object> getAllVuelobyUsuario(@PathVariable Long id) {
        if (usuarioService.checkUsuarioId(id).isPresent()){
            Usuario usuario=usuarioService.checkUsuarioId(id).get();
            List<Vuelo> listVuelos = usuario.getVuelos();
            return new ResponseEntity<>(listVuelos,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No existe un usuario con este Id "+id,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idvuelo}")
    private ResponseEntity<Object> getVuelobyUsuario(@PathVariable Long id,@PathVariable Long idvuelo) {
        if (usuarioService.checkUsuarioId(id).isPresent()){
            if(vueloService.getVueloById(idvuelo).isPresent()){
                Usuario usuario=usuarioService.checkUsuarioId(id).get();
                Vuelo vuelo =vueloService.getVueloById(idvuelo).get();
                if(vuelo.getUsuario().getId().equals(usuario.getId())){
                    return new ResponseEntity<>(vuelo,HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Este vuelo "+idvuelo +" no se ecuentra asociado al usuario "+usuario.getName(),HttpStatus.FORBIDDEN);
                }
            }
            else{
                return new ResponseEntity<>("No existe un Vuelo con este Id "+idvuelo,HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<>("No existe un usuario con este Id "+id,HttpStatus.NOT_FOUND);
        }
    }

}
