package com.conversor.monedas.shared.constants.utils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatUtils {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String formatearMoneda(double cantidad) {
        return DECIMAL_FORMAT.format(cantidad);
    }

    public static String formatearFecha(LocalDateTime fecha) {
        return fecha.format(DATE_FORMAT);
    }

    private FormatUtils() {
        // Clase de utilidades, no debe ser instanciada
    }
}