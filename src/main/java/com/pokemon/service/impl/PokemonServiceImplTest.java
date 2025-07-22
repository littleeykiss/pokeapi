package com.pokemon.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.dto.PokemonResponse;
import com.pokemon.entity.Busqueda;
import com.pokemon.repository.BusquedaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonServiceImplTest {

    private PokemonServiceImpl pokemonService;
    private BusquedaRepository busquedaRepository;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        busquedaRepository = mock(BusquedaRepository.class);
        restTemplate = mock(RestTemplate.class);
        objectMapper = new ObjectMapper();

        pokemonService = new PokemonServiceImpl(busquedaRepository);
    }

    @Test
    public void testObtenerPokemon_ok() throws Exception {
        String nombre = "pikachu";
        String json = """
                {
                  "name": "pikachu",
                  "species": { "name": "pikachu" },
                  "types": [ { "type": { "name": "electric" } } ],
                  "abilities": [ { "ability": { "name": "static" } } ],
                  "moves": [ { "move": { "name": "thunder-punch" } } ],
                  "stats": [
                    { "base_stat": 55, "stat": { "name": "attack" } }
                  ],
                  "sprites": { "front_default": "https://example.com/image.png" }
                }
                """;

        JsonNode jsonNode = objectMapper.readTree(json);

        PokemonServiceImpl serviceSpy = Mockito.spy(pokemonService);
        doReturn(jsonNode).when(serviceSpy).getJsonFromUrl(anyString());
        doReturn("FAKEBASE64==").when(serviceSpy).convertirImagenABase64(anyString());

        PokemonResponse response = serviceSpy.obtenerPokemon(nombre);

        assertEquals("pikachu", response.getNombre());
        assertEquals("pikachu", response.getEspecie());
        assertEquals(1, response.getTipos().size());
        assertEquals(1, response.getHabilidades().size());
        assertEquals(1, response.getAtaques().size());
        assertEquals(1, response.getEstadisticas().size());
        assertEquals("FAKEBASE64==", response.getImagenBase64());

        verify(busquedaRepository, times(1)).save(any(Busqueda.class));
    }
}
