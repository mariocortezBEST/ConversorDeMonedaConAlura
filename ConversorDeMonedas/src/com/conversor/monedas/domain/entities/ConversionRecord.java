package com.conversor.monedas.domain.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionRecord {
    private final String monedaOrigen;
    private final String monedaDestino;
    private final double cantidadOriginal;
    private final double cantidadConvertida;
    private final LocalDateTime timestamp;
    private final double tasaUsada;

    public ConversionRecord(String monedaOrigen, String monedaDestino,
                            double cantidadOriginal, double cantidadConvertida, double tasaUsada) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidadOriginal = cantidadOriginal;
        this.cantidadConvertida = cantidadConvertida;
        this.timestamp = LocalDateTime.now();
        this.tasaUsada = tasaUsada;
    }

    // Getters
    public String getMonedaOrigen() { return monedaOrigen; }
    public String getMonedaDestino() { return monedaDestino; }
    public double getCantidadOriginal() { return cantidadOriginal; }
    public double getCantidadConvertida() { return cantidadConvertida; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getTasaUsada() { return tasaUsada; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %.2f %s → %.2f %s (Tasa: %.4f)",
                timestamp.format(formatter), cantidadOriginal, monedaOrigen,
                cantidadConvertida, monedaDestino, tasaUsada);
    }
}
