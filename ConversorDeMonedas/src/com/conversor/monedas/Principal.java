package com.conversor.monedas;

import com.conversor.monedas.presentation.console.ConversorDeMonedas;
public class Principal {
    public static void main(String[] args) {
        // IMPORTANTE: Reemplaza "YOUR_API_KEY" con tu clave real de ExchangeRate-API
<<<<<<< HEAD
        String apiKey = "your_api_key";
=======
        String apiKey = "your API_key";
>>>>>>> 

        ConversorDeMonedas conversor = new ConversorDeMonedas(apiKey);
        conversor.iniciar();

    }
}
