package com.coca.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coca.tienda.entities.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {

}
