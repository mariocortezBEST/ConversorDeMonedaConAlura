package com.conversor.monedas.infrastrucuture.persistence;

import com.conversor.monedas.domain.entities.ConversionRecord;
import com.conversor.monedas.domain.repositories.HistorialRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryHistorialRepository implements HistorialRepository {
    private final List<ConversionRecord> historial;
    private final int maxRegistros;

    public InMemoryHistorialRepository(int maxRegistros) {
        this.historial = new ArrayList<>();
        this.maxRegistros = maxRegistros;
    }

    @Override
    public void agregarConversion(ConversionRecord record) {
        historial.add(record);

        // Mantener solo los últimos registros
        if (historial.size() > maxRegistros) {
            historial.remove(0);
        }
    }

    @Override
    public List<ConversionRecord> obtenerHistorial() {
        return new ArrayList<>(historial);
    }

    @Override
    public void limpiarHistorial() {
        historial.clear();
    }

    @Override
    public List<ConversionRecord> obtenerHistorialPorMoneda(String codigoMoneda) {
        return historial.stream()
                .filter(record -> record.getMonedaOrigen().equals(codigoMoneda) ||
                        record.getMonedaDestino().equals(codigoMoneda))
                .collect(Collectors.toList());
    }
}