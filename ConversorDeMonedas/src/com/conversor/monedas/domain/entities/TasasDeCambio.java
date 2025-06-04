package com.conversor.monedas.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class TasasDeCambio {
    private String baseCode;
    private Map<String, Double> conversionRates;
    private long lastUpdate;

    public TasasDeCambio(String baseCode, Map<String, Double> conversionRates) {
        this.baseCode = baseCode;
        this.conversionRates = new HashMap<>(conversionRates);
        this.lastUpdate = System.currentTimeMillis();
    }

    public double obtenerTasa(String codigoMoneda) {
        return conversionRates.getOrDefault(codigoMoneda, 0.0);
    }

    public boolean tieneTasa(String codigoMoneda) {
        return conversionRates.containsKey(codigoMoneda);
    }

    // Getters
    public String getBaseCode() { return baseCode; }
    public Map<String, Double> getConversionRates() { return new HashMap<>(conversionRates); }
    public long getLastUpdate() { return lastUpdate; }
}