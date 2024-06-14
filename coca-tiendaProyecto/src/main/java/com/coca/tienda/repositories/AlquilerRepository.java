package com.coca.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coca.tienda.entities.Alquiler;

public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
    
}