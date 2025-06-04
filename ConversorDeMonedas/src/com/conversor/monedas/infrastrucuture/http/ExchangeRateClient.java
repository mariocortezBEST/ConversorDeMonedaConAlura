package com.conversor.monedas.infrastrucuture.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ExchangeRateClient {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient httpClient;
    private final String apiKey;

    public ExchangeRateClient(String apiKey) {
        this.apiKey = apiKey;
        // Sin timeout específico, usará el default del sistema
        this.httpClient = HttpClient.newBuilder().build();
//    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
//    private final HttpClient httpClient;
//    private final String apiKey;
//
//    public ExchangeRateClient(String apiKey) {
//        this.apiKey = apiKey;
//        this.httpClient = HttpClient.newBuilder()
//                .timeout(Duration.ofSeconds(30))
//                .build();
    }

    /**
     * Obtiene las tasas de cambio para una moneda base específica
     */
    public HttpResponse<String> obtenerTasasDeCambio(String monedaBase) throws IOException, InterruptedException {
        String url = BASE_URL + apiKey + "/latest/" + monedaBase;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Realiza una conversión específica entre dos monedas
     */
    public HttpResponse<String> convertirMonedas(String monedaOrigen, String monedaDestino, double cantidad)
            throws IOException, InterruptedException {
        String url = String.format("%s%s/pair/%s/%s/%.2f",
                BASE_URL, apiKey, monedaOrigen, monedaDestino, cantidad);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}