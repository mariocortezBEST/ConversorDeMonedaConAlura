package com.conversor.monedas.domain.entities;

import java.util.Objects;

/**
 * Clase base abstracta para monedas
 */
public abstract class Moneda {
    protected String codigo;
    protected String nombre;
    protected String pais;

    public Moneda(String codigo, String nombre, String pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
    }

    // Método abstracto para validación específica de cada moneda
    public abstract boolean validarCantidad(double cantidad);

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", nombre, codigo, pais);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Moneda moneda = (Moneda) obj;
        return Objects.equals(codigo, moneda.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}