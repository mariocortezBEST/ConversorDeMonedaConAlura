package com.conversor.monedas;

import com.conversor.monedas.presentation.console.ConversorDeMonedas;
public class Principal {
    public static void main(String[] args) {
        // IMPORTANTE: Reemplaza "YOUR_API_KEY" con tu clave real de ExchangeRate-API
        String apiKey = "fe7ce0f2e7765cef4e91c5f6";

        ConversorDeMonedas conversor = new ConversorDeMonedas(apiKey);
        conversor.iniciar();

    }
}
