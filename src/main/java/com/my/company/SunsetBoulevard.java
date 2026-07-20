package com.my.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SunsetBoulevard {
    public static void main(String[] args) {
        /*System.out.println("----- USUARIO -----");*/
        Usuario u1 = new Usuario("Carlos", "Ramirez", "carlos.ramirez@test.com", "7234-6789", "Avenida Los Pinos #45,Santa Ana");

        System.out.println("SOLICITUD 1");
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
        System.out.println("------------------------------------------------------------------");
        simulador1.guardarSolicitudJss();
        System.out.println("------------------------------------------------------------------");

        System.out.println("SOLICITUD 2");
        BigDecimal prestamo2 = new BigDecimal("10000");
        BigDecimal interesPrestamo2 = new BigDecimal("0.10");
        LocalDate fechaI2 = LocalDate.of(2026, 7, 21);
        LocalDate fechaF2 = LocalDate.of(2030, 7, 21);
        SolicitudCredito s2 = new SolicitudCredito(prestamo2, fechaI2 , fechaF2, interesPrestamo2, 48, u1);

        simulador1.guardarSolicitud(u1, s2);
        simulador1.aprobarSolicitud(s2);
        System.out.println("------------------------------------------------------------------");
        simulador1.guardarSolicitudJss();
        System.out.println("------------------------------------------------------------------");
        simulador1.guardarCuotasJss();
        simulador1.pagarCuota(s1, 1);
        System.out.println("CUOTA PAGADA");
        simulador1.guardarCuotasJss();




    }
}
