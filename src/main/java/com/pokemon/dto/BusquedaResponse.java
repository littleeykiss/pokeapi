package com.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BusquedaResponse {
    private String nombrePokemon;
    private LocalDateTime fechaConsulta;
}
