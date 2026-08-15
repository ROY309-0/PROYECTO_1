package com.my.company.TEST.PERSISTENCIA;

import com.my.company.SimuladorCredito;
import com.my.company.SolicitudCredito;
import com.my.company.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prueba01Guardar {
    public static void main(String[] args) {

        /*Prueba de guardado
        En Prueba01Guardar:
        1. Crear un SimuladorCredito.
        2. Crear un usuario.
        3. Guardarlo en el gestor.
        4. Crear una solicitud asociada.
        5. Guardarla.
        6. Aprobarla para generar cuotas.
        7. Pagar una cuota.
        8. Guardar usuarios, solicitudes y cuotas en JSON.
        9. Terminar el programa.*/
        //Creación de objeto SimuladorCredito
        SimuladorCredito simuladorCredito1 = new SimuladorCredito();
        //Creación de un usuario
        Usuario usuario1 = new Usuario("Carlos", "Ramirez", "carlos.ramirez@test.com", "7234-6789", "Avenida Los Pinos #45,Santa Ana");
        //Guardar Usuario en su gestor correspondiente
        simuladorCredito1.guardarUsuario(usuario1);
        //Crear una solicitud asociada
        SolicitudCredito solicitudCredito1 = new SolicitudCredito(new BigDecimal("1000"), LocalDate.of(2026, 6, 16), LocalDate.of(2027, 6, 16), new BigDecimal("0.05"), 12, usuario1);
        System.out.println(solicitudCredito1.toString());
        //Guardar la solicitud
        simuladorCredito1.guardarSolicitud(usuario1, solicitudCredito1);
        //Aprobar solicitud
        simuladorCredito1.aprobarSolicitud(solicitudCredito1);
        System.out.println(solicitudCredito1.toString());
        //Aprobarla para generar cuotas
        System.out.println(simuladorCredito1.getCuotasDeSolicitud(solicitudCredito1));
        //Pagar cuota
        simuladorCredito1.pagarCuota(solicitudCredito1, 1);
        System.out.println(simuladorCredito1.getCuotasDeSolicitud(solicitudCredito1));
        //Guardar en los JSon
        simuladorCredito1.guardarUsuarioJs();
        simuladorCredito1.guardarSolicitudJss();
        simuladorCredito1.guardarCuotasJss();
    }
}
