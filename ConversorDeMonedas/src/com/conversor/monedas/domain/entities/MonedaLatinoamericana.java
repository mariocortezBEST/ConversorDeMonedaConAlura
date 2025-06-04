package com.conversor.monedas.domain.entities;

public class MonedaLatinoamericana extends Moneda {
    private boolean esVolatil;

    public MonedaLatinoamericana(String codigo, String nombre, String pais, boolean esVolatil) {
        super(codigo, nombre, pais);
        this.esVolatil = esVolatil;
    }

    @Override
    public boolean validarCantidad(double cantidad) {
        // Validación específica para monedas latinoamericanas
        return cantidad > 0 && cantidad <= 1000000; // Límite alto por volatilidad
    }

    public boolean esVolatil() { return esVolatil; }
}
