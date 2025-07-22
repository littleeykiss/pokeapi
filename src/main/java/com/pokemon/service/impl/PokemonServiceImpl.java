package com.pokemon.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.dto.BusquedaResponse;
import com.pokemon.dto.Estadistica;
import com.pokemon.dto.PokemonResponse;
import com.pokemon.entity.Busqueda;
import com.pokemon.repository.BusquedaRepository;
import com.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BusquedaRepository busquedaRepository;

    @Override
    public PokemonResponse obtenerPokemon(String nombre) throws Exception {
        String url = "https://pokeapi.co/api/v2/pokemon/" + nombre.toLowerCase();
        JsonNode json = restTemplate.getForObject(url, JsonNode.class);

        String nombrePokemon = json.get("name").asText();
        String especie = json.get("species").get("name").asText();

        List<String> tipos = json.get("types").findValues("type")
                .stream().map(n -> n.get("name").asText()).collect(Collectors.toList());

        List<String> habilidades = json.get("abilities").findValues("ability")
                .stream().map(n -> n.get("name").asText()).collect(Collectors.toList());

        List<String> ataques = json.get("moves").findValues("move")
                .stream().map(n -> n.get("name").asText()).limit(5).collect(Collectors.toList());

        List<Estadistica> estadisticas = json.get("stats")
                .findParents("base_stat").stream().map(stat -> {
                    String statName = stat.get("stat").get("name").asText();
                    int valor = stat.get("base_stat").asInt();
                    return new Estadistica(statName, valor);
                }).collect(Collectors.toList());

        String imageUrl = json.get("sprites").get("front_default").asText();
        String imagenBase64 = convertirImagenABase64(imageUrl);

        busquedaRepository.save(Busqueda.builder()
                .nombrePokemon(nombrePokemon)
                .fechaConsulta(LocalDateTime.now())
                .build());

        return PokemonResponse.builder()
                .nombre(nombrePokemon)
                .especie(especie)
                .tipos(tipos)
                .habilidades(habilidades)
                .ataques(ataques)
                .estadisticas(estadisticas)
                .imagenBase64(imagenBase64)
                .build();
    }

    protected String convertirImagenABase64(String urlImagen) throws Exception {
        byte[] bytes = new URL(urlImagen).openStream().readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    protected JsonNode getJsonFromUrl(String url) throws Exception {
        return restTemplate.getForObject(url, JsonNode.class);
    }

    @Override
    public List<BusquedaResponse> obtenerHistorial() {
        return busquedaRepository.findAll().stream()
                .map(b -> BusquedaResponse.builder()
                        .nombrePokemon(b.getNombrePokemon())
                        .fechaConsulta(b.getFechaConsulta())
                        .build())
                .toList();
    }

}
