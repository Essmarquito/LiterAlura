package com.alura.LiterAlura.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumirApi {

    public String obtenerDatos(String url) {
        // Verificar si la URL es válida
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("La URL no puede ser nula ni estar vacía.");
        }

        // Crear el cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        // Crear la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response;
        try {
            // Enviar la solicitud y obtener la respuesta
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el código de estado HTTP
            if (response.statusCode() != 200) {
                System.out.println("Error: Código de estado HTTP " + response.statusCode());
                return null;
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consumir la API: " + e.getMessage(), e);
        }

        // Retornar el cuerpo de la respuesta (el contenido de la API)
        return response.body();
    }
}
