package com.conversor.monedas.application.services;

import com.conversor.monedas.domain.entities.Moneda;

import java.util.Collection;

public interface ConversionService {
    double convertir(String monedaOrigen, String monedaDestino, double cantidad);
    boolean soportaMoneda(String codigoMoneda);
    void actualizarTasas();
    Collection<Moneda> obtenerMonedasSoportadas();
}