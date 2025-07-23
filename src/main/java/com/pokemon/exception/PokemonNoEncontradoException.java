package com.pokemon.exception;

public class PokemonNoEncontradoException extends RuntimeException {
    public PokemonNoEncontradoException(String nombre) {
        super("No se encontró el pokémon: " + nombre);
    }
}