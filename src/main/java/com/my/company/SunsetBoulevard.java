package com.my.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SunsetBoulevard {
    public static void main(String[] args) {
        /*System.out.println("----- USUARIO -----");*/
        Usuario u1 = new Usuario("Juan", "Perez", "juan@gmail.com", "123456", "Calle 1");
        //Usuario u2 = new Usuario("Kallax", "Calling", "kallax123@gmail.com", "7123-2884", "Col, Las Nieves");
        //System.out.println(u1.toString());

        BigDecimal prestamo = new BigDecimal("    ");
        BigDecimal interesPrestamo = new BigDecimal("0.05");
        LocalDate fechaI = LocalDate.of(2024, 1, 1);
        LocalDate fechaF = LocalDate.of(2024, 12, 1);
        SolicitudCredito s1 = new SolicitudCredito(prestamo, fechaI , fechaF, interesPrestamo, 11, u1);

        SimuladorCredito simulador1 = new SimuladorCredito();
        simulador1.gestorSolicitudes.guardarSolicitud(u1, s1);
        System.out.println(simulador1.gestorSolicitudes.getSolicitudesAsociadasUsuario(u1));
    }
}
