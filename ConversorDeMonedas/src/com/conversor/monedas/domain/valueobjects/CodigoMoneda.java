package com.conversor.monedas.domain.valueobjects;

import java.util.Objects;

public class CodigoMoneda {
    private final String valor;

    public CodigoMoneda(String valor) {
        if (valor == null || valor.length() != 3 || !valor.equals(valor.toUpperCase())) {
            throw new IllegalArgumentException("Código de moneda debe tener 3 letras mayúsculas");
        }
        this.valor = valor;
    }

    public String getValor() { return valor; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CodigoMoneda that = (CodigoMoneda) obj;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
