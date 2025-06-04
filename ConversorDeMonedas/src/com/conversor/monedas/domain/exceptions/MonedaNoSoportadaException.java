package com.conversor.monedas.domain.exceptions;

public class MonedaNoSoportadaException extends RuntimeException {
    public MonedaNoSoportadaException(String codigoMoneda) {
        super("Moneda no soportada: " + codigoMoneda);
    }
}
