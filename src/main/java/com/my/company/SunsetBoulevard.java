package com.my.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SunsetBoulevard {
    public static void main(String[] args) {
        Usuario u1 = new Usuario("Juan", "Benito", "juanito@gmail.com", "6118-2284", "Col, La Gloria");
        System.out.println(u1.toString());

        BigDecimal prestamo = new BigDecimal("12000.00");
        BigDecimal interesPrestamo = new BigDecimal("12.00");
        LocalDate fechaI = LocalDate.of(2024, 12, 21);
        LocalDate fechaF = LocalDate.of(2024, 12, 21);
        SolicitudCredito s1 = new SolicitudCredito(prestamo, "ACTIVA", fechaI , fechaF, interesPrestamo, 48, u1);
        System.out.println(s1.toString());

    }
}
