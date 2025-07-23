package com.pokemon.service;

import com.pokemon.dto.BusquedaResponse;
import com.pokemon.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {
    PokemonResponse obtenerPokemon(String nombre) throws Exception;
    List<BusquedaResponse> obtenerHistorial();

}
