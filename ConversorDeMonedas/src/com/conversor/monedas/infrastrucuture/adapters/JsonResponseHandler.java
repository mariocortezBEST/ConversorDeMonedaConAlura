package com.conversor.monedas.infrastrucuture.adapters;

import com.conversor.monedas.domain.entities.TasasDeCambio;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class JsonResponseHandler {
    private final Gson gson;

    public JsonResponseHandler() {
        this.gson = new Gson();
    }

    public TasasDeCambio procesarRespuesta(String respuestaJson) {
        try {
            JsonObject jsonObject = JsonParser.parseString(respuestaJson).getAsJsonObject();

            // Verificar si la respuesta fue exitosa
            if (!jsonObject.get("result").getAsString().equals("success")) {
                throw new RuntimeException("Error en la API: " +
                        jsonObject.get("error-type").getAsString());
            }

            String baseCode = jsonObject.get("base_code").getAsString();
            JsonObject ratesObject = jsonObject.getAsJsonObject("conversion_rates");

            Map<String, Double> tasas = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : ratesObject.entrySet()) {
                tasas.put(entry.getKey(), entry.getValue().getAsDouble());
            }

            return new TasasDeCambio(baseCode, tasas);

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar respuesta JSON: " + e.getMessage(), e);
        }
    }
}
