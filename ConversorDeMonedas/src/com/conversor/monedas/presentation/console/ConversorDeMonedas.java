package com.conversor.monedas.presentation.console;

import com.conversor.monedas.application.services.ConversionService;
import com.conversor.monedas.application.services.ExchangeRateConversionService;
import com.conversor.monedas.domain.entities.ConversionRecord;
import com.conversor.monedas.domain.entities.Moneda;
import com.conversor.monedas.domain.entities.TasasDeCambio;
import com.conversor.monedas.domain.repositories.HistorialRepository;
import com.conversor.monedas.infrastrucuture.persistence.InMemoryHistorialRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ConversorDeMonedas {
    private final ConversionService conversionService;
    private final HistorialRepository historialRepository;
    private final Scanner scanner;

    public ConversorDeMonedas(String apiKey) {
        this.conversionService = new ExchangeRateConversionService(apiKey);
        this.historialRepository = new InMemoryHistorialRepository(20);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=================================");
        System.out.println("Sea bienvenido/a al Conversor de Moneda");
        System.out.println("=================================\n");

        try {
            conversionService.actualizarTasas();
            mostrarMenuPrincipal();
        } catch (Exception e) {
            System.err.println("Error al inicializar el conversor: " + e.getMessage());
        }
    }

    private void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Conversión personalizada");
            System.out.println("8) Ver historial de conversiones");
            System.out.println("9) Limpiar historial");
            System.out.println("0) Salir");
            System.out.print("Elija una opción válida: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> realizarConversion("USD", "ARS");
                case 2 -> realizarConversion("ARS", "USD");
                case 3 -> realizarConversion("USD", "BRL");
                case 4 -> realizarConversion("BRL", "USD");
                case 5 -> realizarConversion("USD", "COP");
                case 6 -> realizarConversion("COP", "USD");
                case 7 -> mostrarMenuConversionPersonalizada();
                case 8 -> mostrarHistorial();
                case 9 -> limpiarHistorial();
                case 0 -> System.out.println("¡Gracias por usar el Conversor de Monedas!");
                default -> System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuConversionPersonalizada() {
        Collection<Moneda> monedas = conversionService.obtenerMonedasSoportadas();

        System.out.println("\n--- MONEDAS DISPONIBLES ---");
        List<Moneda> listaMonedas = new ArrayList<>(monedas);
        for (int i = 0; i < listaMonedas.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, listaMonedas.get(i));
        }

        System.out.print("Seleccione moneda origen (número): ");
        int origenIndex = scanner.nextInt() - 1;

        System.out.print("Seleccione moneda destino (número): ");
        int destinoIndex = scanner.nextInt() - 1;

        if (origenIndex >= 0 && origenIndex < listaMonedas.size() &&
                destinoIndex >= 0 && destinoIndex < listaMonedas.size()) {

            String monedaOrigen = listaMonedas.get(origenIndex).getCodigo();
            String monedaDestino = listaMonedas.get(destinoIndex).getCodigo();

            realizarConversion(monedaOrigen, monedaDestino);
        } else {
            System.out.println("Selección inválida.");
        }
    }

    private void realizarConversion(String monedaOrigen, String monedaDestino) {
        try {
            System.out.printf("Ingrese el valor que desea convertir de %s: ", monedaOrigen);
            double cantidad = scanner.nextDouble();

            double resultado = conversionService.convertir(monedaOrigen, monedaDestino, cantidad);

            // Obtener la tasa para el historial
            ExchangeRateConversionService service = (ExchangeRateConversionService) conversionService;
            TasasDeCambio tasas = service.getTasasActuales();
            double tasaOrigen = tasas.obtenerTasa(monedaOrigen);
            double tasaDestino = tasas.obtenerTasa(monedaDestino);
            double tasaUsada = tasaDestino / tasaOrigen;

            System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n",
                    cantidad, monedaOrigen, resultado, monedaDestino);

            // Guardar en historial
            ConversionRecord record = new ConversionRecord(monedaOrigen, monedaDestino,
                    cantidad, resultado, tasaUsada);
            historialRepository.agregarConversion(record);

        } catch (Exception e) {
            System.err.println("Error al realizar conversión: " + e.getMessage());
        }
    }

    private void mostrarHistorial() {
        List<ConversionRecord> historial = historialRepository.obtenerHistorial();

        if (historial.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
            return;
        }

        System.out.println("\n--- HISTORIAL DE CONVERSIONES ---");
        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.printf("%d. %s%n", historial.size() - i, historial.get(i));
        }
    }

    private void limpiarHistorial() {
        historialRepository.limpiarHistorial();
        System.out.println("Historial limpiado exitosamente.");
    }
}