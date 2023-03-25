package com.uniandes.tesis.demo.dataaccess.repository;

import com.uniandes.tesis.demo.dataaccess.model.TakenPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakenPhotoRepository extends JpaRepository<TakenPhoto, Long> {
}
