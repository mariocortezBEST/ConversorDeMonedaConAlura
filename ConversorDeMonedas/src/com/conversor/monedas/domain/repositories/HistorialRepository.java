package com.conversor.monedas.domain.repositories;

import com.conversor.monedas.domain.entities.ConversionRecord;

import java.util.List;

public interface HistorialRepository {
    void agregarConversion(ConversionRecord record);
    List<ConversionRecord> obtenerHistorial();
    void limpiarHistorial();
    List<ConversionRecord> obtenerHistorialPorMoneda(String codigoMoneda);
}