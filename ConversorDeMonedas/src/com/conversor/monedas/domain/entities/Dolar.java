package com.conversor.monedas.domain.entities;

public class Dolar extends Moneda {
    public Dolar() {
        super("USD", "Dólar estadounidense", "Estados Unidos");
    }

    @Override
    public boolean validarCantidad(double cantidad) {
        return cantidad > 0 && cantidad <= 100000;
    }
}