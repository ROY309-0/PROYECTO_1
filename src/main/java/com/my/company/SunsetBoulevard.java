package com.my.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SunsetBoulevard {
    public static void main(String[] args) {
        System.out.println("----- USUARIO -----");
        Usuario u1 = new Usuario("Juan", "Benito", "juanito@gmail.com", "6118-2284", "Col, La Gloria");
        Usuario u2 = new Usuario("Kallax", "Calling", "kallax123@gmail.com", "7123-2884", "Col, Las Nieves");
        System.out.println(u1.toString());

        BigDecimal prestamo = new BigDecimal("12000.00");
        BigDecimal interesPrestamo = new BigDecimal("12.00");
        LocalDate fechaI = LocalDate.of(2024, 12, 21);
        LocalDate fechaF = LocalDate.of(2028, 12, 21);
        SolicitudCredito s1 = new SolicitudCredito(prestamo, fechaI , fechaF, interesPrestamo, 48, u1);
        System.out.println("----- SOLICITUD -----");
        System.out.println(s1.toString());

        BigDecimal prestamo2 = new BigDecimal("1200.00");
        BigDecimal interesPrestamo2 = new BigDecimal("12.00");
        LocalDate fechaI2 = LocalDate.of(2029, 12, 21);
        LocalDate fechaF2 = LocalDate.of(2031, 12, 21);
        SolicitudCredito s2 = new SolicitudCredito(prestamo2,  fechaI2 , fechaF2, interesPrestamo2, 24, u1);
        System.out.println("----- SOLICITUD -----");
        System.out.println(s2.toString());

        BigDecimal prestamo3 = new BigDecimal("120000.00");
        BigDecimal interesPrestamo3 = new BigDecimal("17.00");
        LocalDate fechaI3 = LocalDate.of(2025, 12, 21);
        LocalDate fechaF3 = LocalDate.of(2051, 12, 21);
        SolicitudCredito s3 = new SolicitudCredito(prestamo3, fechaI3 , fechaF3, interesPrestamo3, 312, u2);
        System.out.println("----- SOLICITUD -----");
        System.out.println(s3.toString());

        System.out.println("-----SOLICITUD - USUARIO -----");
        SimuladorCredito sc1 = new SimuladorCredito();
        System.out.println("-------------------------");
        sc1.guardarSolicitud(u1, s1);
        System.out.println(sc1.getCuotasDeSolicitud(s1));
        sc1.guardarSolicitud(u1, s2);
        System.out.println(sc1.getCuotasDeSolicitud(s2));
        sc1.guardarSolicitud(u2, s3);
        System.out.println(sc1.getCuotasDeSolicitud(s3));
        System.out.println("-------------------------");
        System.out.println(u1.getNombre()+" : " + sc1.getSolicitudesAsociadasUsuario(u1));
        System.out.println("ESTADO SOLICITUDES");
        System.out.println(s1.getEstado());


    }
}
