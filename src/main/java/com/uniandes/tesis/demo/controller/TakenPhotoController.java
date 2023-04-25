package com.uniandes.tesis.demo.controller;

import com.uniandes.tesis.demo.dataaccess.model.TakenPhoto;
import com.uniandes.tesis.demo.dataaccess.model.Usuario;
import com.uniandes.tesis.demo.dataaccess.model.Vuelo;
import com.uniandes.tesis.demo.service.TakenPhotoService;
import com.uniandes.tesis.demo.service.UsuarioService;
import com.uniandes.tesis.demo.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/usuario/{id}/vuelo/{idVuelo}/TakenPhoto")
public class TakenPhotoController {

    @Autowired
    private VueloService vueloService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TakenPhotoService takenPhotoService;

    @PostMapping
    public ResponseEntity<Object> createTakenPhoto(@PathVariable Long id, @PathVariable Long idVuelo,@RequestParam("photo") MultipartFile photo){
        if (usuarioService.checkUsuarioId(id).isPresent()){
            if(vueloService.getVueloById(idVuelo).isPresent()){
                if(vueloService.getVueloById(idVuelo).get().getUsuario().equals(usuarioService.checkUsuarioId(id).get())){
                    TakenPhoto takenPhoto = new TakenPhoto();
                    takenPhoto.setVuelo(vueloService.getVueloById(idVuelo).get());
                    vueloService.getVueloById(idVuelo).get().getTakenPhotos().add(takenPhoto);
                    Path folder = Paths.get("src//main//resources/images");
                    String finalFolder = folder.toFile().getAbsolutePath();
                    takenPhoto.setRuta(finalFolder+"//"+photo.getOriginalFilename());
                    byte[] data = null;
                    try {
                         data = photo.getBytes();
                         Path completePath = Paths.get(finalFolder+"//"+photo.getOriginalFilename());
                        Files.write(completePath, data);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    takenPhoto.setNombre(photo.getOriginalFilename());
                    takenPhoto.setData(data);
                    takenPhoto=takenPhotoService.createTakenPhoto(takenPhoto);
                    return new ResponseEntity<>(takenPhoto,HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>("No existe un vuelo con este Id "+id +"asociado a este usuario "+usuarioService.checkUsuarioId(id).get().getName(),HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("No existe un vuelo con este Id "+id,HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<>("No existe un usuario con este Id "+id,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{idTakenPhoto}")
    public ResponseEntity<Object> obtenerImagen(@PathVariable Long id, @PathVariable Long idVuelo, @PathVariable Long idTakenPhoto) {
        if (usuarioService.checkUsuarioId(id).isPresent()){
            if(vueloService.getVueloById(idVuelo).isPresent()){
                if(vueloService.getVueloById(idVuelo).get().getUsuario().equals(usuarioService.checkUsuarioId(id).get())){
                        TakenPhoto takenPhoto = takenPhotoService.findTakenPhoto(idTakenPhoto).get();
                        return new ResponseEntity<>(takenPhoto,HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("No existe un vuelo con este Id "+idVuelo +"asociado a este usuario "+usuarioService.checkUsuarioId(id).get().getName(),HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("No existe un vuelo con este Id "+id,HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<>("No existe un usuario con este Id "+id,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/finalPhoto/{idTakenPhoto}")
    public ResponseEntity<Object> obtenerImagenFinal(@PathVariable Long id, @PathVariable Long idVuelo, @PathVariable Long idTakenPhoto) {
        if (usuarioService.checkUsuarioId(id).isPresent()){
            if(vueloService.getVueloById(idVuelo).isPresent()){
                if(vueloService.getVueloById(idVuelo).get().getUsuario().equals(usuarioService.checkUsuarioId(id).get())){
                    TakenPhoto takenPhoto = takenPhotoService.findTakenPhoto(idTakenPhoto).get();
                    Resource file = new FileSystemResource(takenPhoto.getRuta());
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
                }else{
                    return new ResponseEntity<>("No existe un vuelo con este Id "+idVuelo +"asociado a este usuario "+usuarioService.checkUsuarioId(id).get().getName(),HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("No existe un vuelo con este Id "+id,HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<>("No existe un usuario con este Id "+id,HttpStatus.NOT_FOUND);
        }
    }



}




