package com.conversor.monedas.application.services;

import com.conversor.monedas.domain.entities.Dolar;
import com.conversor.monedas.domain.entities.Moneda;
import com.conversor.monedas.domain.entities.MonedaLatinoamericana;
import com.conversor.monedas.domain.entities.TasasDeCambio;
import com.conversor.monedas.domain.exceptions.CantidadInvalidaException;
import com.conversor.monedas.domain.exceptions.MonedaNoSoportadaException;
import com.conversor.monedas.infrastrucuture.adapters.JsonResponseHandler;
import com.conversor.monedas.infrastrucuture.http.ExchangeRateClient;

import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRateConversionService implements ConversionService {
    private final ExchangeRateClient client;
    private final JsonResponseHandler responseHandler;
    private final Map<String, Moneda> monedasSoportadas;
    private TasasDeCambio tasasActuales;

    public ExchangeRateConversionService(String apiKey) {
        this.client = new ExchangeRateClient(apiKey);
        this.responseHandler = new JsonResponseHandler();
        this.monedasSoportadas = inicializarMonedas();
    }

    private Map<String, Moneda> inicializarMonedas() {
        Map<String, Moneda> monedas = new HashMap<>();

        // Monedas latinoamericanas
        monedas.put("ARS", new MonedaLatinoamericana("ARS", "Peso argentino", "Argentina", true));
        monedas.put("BOB", new MonedaLatinoamericana("BOB", "Boliviano boliviano", "Bolivia", false));
        monedas.put("BRL", new MonedaLatinoamericana("BRL", "Real brasileño", "Brasil", false));
        monedas.put("CLP", new MonedaLatinoamericana("CLP", "Peso chileno", "Chile", false));
        monedas.put("COP", new MonedaLatinoamericana("COP", "Peso colombiano", "Colombia", false));

        // Dólar estadounidense
        monedas.put("USD", new Dolar());

        return monedas;
    }

    @Override
    public double convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        if (!soportaMoneda(monedaOrigen) || !soportaMoneda(monedaDestino)) {
            throw new MonedaNoSoportadaException(monedaOrigen + " o " + monedaDestino);
        }

        Moneda moneda = monedasSoportadas.get(monedaOrigen);
        if (!moneda.validarCantidad(cantidad)) {
            throw new CantidadInvalidaException("Cantidad inválida para la moneda " + monedaOrigen);
        }

        try {
            if (tasasActuales == null) {
                actualizarTasas();
            }

            if (monedaOrigen.equals(monedaDestino)) {
                return cantidad;
            }

            double tasaOrigen = tasasActuales.obtenerTasa(monedaOrigen);
            double tasaDestino = tasasActuales.obtenerTasa(monedaDestino);

            // Convertir primero a USD (moneda base) y luego a la moneda destino
            double cantidadEnUSD = cantidad / tasaOrigen;
            return cantidadEnUSD * tasaDestino;

        } catch (Exception e) {
            throw new RuntimeException("Error al realizar conversión: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean soportaMoneda(String codigoMoneda) {
        return monedasSoportadas.containsKey(codigoMoneda);
    }

    @Override
    public void actualizarTasas() {
        try {
            HttpResponse<String> response = client.obtenerTasasDeCambio("USD");
            this.tasasActuales = responseHandler.procesarRespuesta(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar tasas: " + e.getMessage(), e);
        }
    }

    @Override
    public Collection<Moneda> obtenerMonedasSoportadas() {
        return monedasSoportadas.values();
    }

    public TasasDeCambio getTasasActuales() {
        return tasasActuales;
    }
}