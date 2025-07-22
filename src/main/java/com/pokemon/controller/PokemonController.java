package com.pokemon.controller;

import com.pokemon.dto.BusquedaResponse;
import com.pokemon.dto.PokemonResponse;
import com.pokemon.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
@CrossOrigin
public class PokemonController {

    private final PokemonService pokemonService;

    @Operation(
            summary = "Buscar Pokémon por nombre",
            description = "Consulta datos del Pokémon (especie, tipos, habilidades, ataques, estadísticas e imagen en base64) usando la PokeAPI"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokémon encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PokemonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pokémon no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content)
    })
    @GetMapping("/{nombre}")
    public ResponseEntity<PokemonResponse> getPokemon(@PathVariable @NotBlank String nombre) {
        try {
            PokemonResponse response = pokemonService.obtenerPokemon(nombre);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); //Este tiene mejor uso en el GlobalExceptions
        }
    }

    @Operation(
            summary = "Consultar historial de búsquedas",
            description = "Devuelve una lista de todos los Pokémon buscados, con fecha y hora de consulta"
    )
    @ApiResponse(responseCode = "200", description = "Historial recuperado correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BusquedaResponse.class)))
    @GetMapping("/busquedas")
    public ResponseEntity<List<BusquedaResponse>> getHistorial() {
        return ResponseEntity.ok(pokemonService.obtenerHistorial());
    }
}
