package com.pokemon.repository;

import com.pokemon.entity.Busqueda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusquedaRepository extends JpaRepository<Busqueda, Long> {
}
