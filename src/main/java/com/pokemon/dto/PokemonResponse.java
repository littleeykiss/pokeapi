package com.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonResponse {
    private String nombre;
    private String especie;
    private List<String> tipos;
    private List<String> habilidades;
    private List<String> ataques;
    private List<Estadistica> estadisticas;
    private String imagenBase64;
}
