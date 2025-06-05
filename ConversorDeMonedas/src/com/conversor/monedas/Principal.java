package com.conversor.monedas;

import com.conversor.monedas.presentation.console.ConversorDeMonedas;
public class Principal {
    public static void main(String[] args) {
        // IMPORTANTE: Reemplaza "YOUR_API_KEY" con tu clave real de ExchangeRate-API
<<<<<<< HEAD
        String apiKey = "your_api_key";
=======
        String apiKey = "your API_key";
>>>>>>> f92cabddf7cae1a300e8b33f6f7d0c2daacc179d

        ConversorDeMonedas conversor = new ConversorDeMonedas(apiKey);
        conversor.iniciar();

    }
}
