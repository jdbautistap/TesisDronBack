package com.uniandes.tesis.demo.controller;

import com.uniandes.tesis.demo.dataaccess.model.Coordinate;
import com.uniandes.tesis.demo.dataaccess.model.Usuario;
import com.uniandes.tesis.demo.dataaccess.model.Vuelo;
import com.uniandes.tesis.demo.service.UsuarioService;
import com.uniandes.tesis.demo.service.UtilsService;
import com.uniandes.tesis.demo.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("api/usuario/{id}/vuelo")
public class VueloController {

    @Autowired
    private VueloService vueloService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UtilsService utilsService;


    @PostMapping
    private ResponseEntity<Object> saveVuelo(@PathVariable Long id, @RequestBody Vuelo vuelo) {
        if (usuarioService.checkUsuarioId(id).isPresent()){
            Usuario usuario=usuarioService.checkUsuarioId(id).get();
            String missionCode="";
            if(vuelo.getMissionCode()==null){
                missionCode = UUID.randomUUID().toString();
            }
            vuelo.setMissionCode(missionCode);
            vuelo.setUsuario(usuario);
            Vuelo tmp =  vueloService.createVuelo(vuelo);
            usuario.getVuelos().add(tmp);
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
            List<Vuelo>listafinal=new ArrayList<>();
            List<Vuelo> vuelosDB =vueloService.obtenerTodosLosVuelos();
            for(Vuelo v:vuelosDB){
                if(v.getUsuario().equals(usuario)){
                    listafinal.add(v);
                }
            }
            return new ResponseEntity<>(listafinal,HttpStatus.OK);
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

    @PutMapping ("/{idvuelo}/finishing")
    private ResponseEntity<Object> actualizarVuelobyUsuario(@PathVariable Long id,@PathVariable Long idvuelo,@RequestBody Vuelo pVuelo) {
        if (usuarioService.checkUsuarioId(id).isPresent()){
            if(vueloService.getVueloById(idvuelo).isPresent()){
                Usuario usuario=usuarioService.checkUsuarioId(id).get();
                Vuelo vuelo =vueloService.getVueloById(idvuelo).get();
                if(vuelo.getUsuario().getId().equals(usuario.getId())){
                    vuelo.setLocationFinishLat(pVuelo.getLocationFinishLat());
                    vuelo.setLocationFinishLng(pVuelo.getLocationFinishLng());
                    vuelo.setFinishdate(pVuelo.getFinishdate());
                    vuelo.setCoordinates(pVuelo.getCoordinates());
                    long timeInMillis1 = vuelo.getDate().getTime();
                    long timeInMillis2 = vuelo.getFinishdate().getTime();
                    long differenceInMillis = timeInMillis2 - timeInMillis1;
                    long differenceInDays = TimeUnit.MILLISECONDS.toSeconds(differenceInMillis);
                    vuelo.setDuration(differenceInDays+"");
                    vueloService.createVuelo(vuelo);
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
