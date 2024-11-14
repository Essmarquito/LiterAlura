package com.alura.LiterAlura.model;

import lombok.Getter;

import java.text.Normalizer;
import java.util.Arrays;

public enum Lenguaje {
    ESPANIOL("es", "Español"),
    INGLES("en", "Inglés"),
    PORTUGUES("pt", "Portugués"),
    FRANCES("fr", "Francés"),
    ITALIANO("it", "Italiano"),
    ALEMAN("de", "Alemán");

    @Getter
    private final String lenguajeGutendex;
    @Getter
    private final String lenguajeEspanol;

    Lenguaje(String lenguajeGutendex, String lenguajeEspanol) {
        this.lenguajeGutendex = lenguajeGutendex;
        this.lenguajeEspanol = lenguajeEspanol;
    }

    // Método para obtener el enum basado en el código de Gutendex
    public static Lenguaje fromGutendex(String texto) {
        return Arrays.stream(Lenguaje.values())
                .filter(lenguaje -> lenguaje.lenguajeGutendex.equals(texto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ningún lenguaje encontrado: " + texto));
    }

    // Método para obtener el enum basado en el nombre en español
    public static Lenguaje fromEspanol(String texto) {
        String textoNormalizado = normalizarTexto(texto);
        return Arrays.stream(Lenguaje.values())
                .filter(lenguaje -> normalizarTexto(lenguaje.lenguajeEspanol).equals(textoNormalizado))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ningún lenguaje encontrado: " + texto + ". Lenguajes disponibles: " +
                        Arrays.toString(Lenguaje.values())));
    }

    // Método auxiliar para normalizar texto (eliminar acentos y convertir a minúsculas)
    private static String normalizarTexto(String input) {
        if (input == null) {
            return null;
        }
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
}
