package com.uniandes.tesis.demo.service;

import com.uniandes.tesis.demo.dataaccess.model.TakenPhoto;
import com.uniandes.tesis.demo.dataaccess.repository.TakenPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TakenPhotoService {

    @Autowired
    private TakenPhotoRepository takenPhotoRepository;

    public TakenPhoto createTakenPhoto(TakenPhoto takenPhoto){
        return takenPhotoRepository.save(takenPhoto);
    }

    public Optional<TakenPhoto> findTakenPhoto(Long id){
        return takenPhotoRepository.findById(id);
    }

    /*public TakenPhoto updateTakenPhoto(){
        // Buscar el usuario existente en la base de datos por su ID
        TakenPhoto takenPhotoExistente = takenPhotoRepository.findById(takenPhoto.getId()).orElse(null);

        if (takenPhotoExistente != null) {
            // Actualizar los datos del usuario existente con los nuevos datos
            takenPhotoExistente.setVuelo(takenPhoto.getNombre());

            // Guardar el usuario actualizado en la base de datos
            return usuarioRepository.save(usuarioExistente);
        } else {
            // Manejar el caso si el usuario no existe en la base de datos
            throw new RuntimeException("Usuario no encontrado: " + usuario.getId());
        }
    }*/

}
