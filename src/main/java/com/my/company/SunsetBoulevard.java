package com.my.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SunsetBoulevard {
    public static void main(String[] args) {
        /*System.out.println("----- USUARIO -----");*/
        Usuario u1 = new Usuario("Carlos", "Ramirez", "carlos.ramirez@test.com", "7234-6789", "Avenida Los Pinos #45,Santa Ana");

        BigDecimal prestamo = new BigDecimal("1000");
        BigDecimal interesPrestamo = new BigDecimal("0.05");
        LocalDate fechaI = LocalDate.of(2026, 6, 16);
        LocalDate fechaF = LocalDate.of(2027, 6, 15);
        SolicitudCredito s1 = new SolicitudCredito(prestamo, fechaI , fechaF, interesPrestamo, 11, u1);

        SimuladorCredito simulador1 = new SimuladorCredito();
        simulador1.guardarSolicitud(u1, s1);
        simulador1.aprobarSolicitud(s1);
        System.out.println(simulador1.estadoSolicitud(s1));
        System.out.println(simulador1.getCuotasDeSolicitud(s1));

    }
}
