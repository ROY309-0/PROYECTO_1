package com.my.company.TEST.PERSISTENCIA;

import com.my.company.Cuota;
import com.my.company.SimuladorCredito;
import com.my.company.SolicitudCredito;
import com.my.company.Usuario;

import java.util.List;

public class Prueba03Verificar {
    public static void main(String[] args) {
        // 1. Crear un simulador nuevo
        SimuladorCredito simuladorCreditoVerificar = new SimuladorCredito();
        // 2. Cargar el sistema
        simuladorCreditoVerificar.cargar();
        // 3. Buscar al usuario 1
        Usuario usuarioV = simuladorCreditoVerificar.buscarUsuario(1);
        System.out.println("Usuario encontrado: "+ usuarioV.toString());
        // 4. Consultar sus solicitudes
        List<SolicitudCredito> solicitudes = simuladorCreditoVerificar.getSolicitudesAsociadasUsuario(usuarioV);
        System.out.println(solicitudes.toString());
        // 5. Tomar la solicitud utilizada en las pruebas
        SolicitudCredito solicitudCreditoV = solicitudes.get(0);
        // 6. Consultar sus cuotas
        List<Cuota> cuotas = simuladorCreditoVerificar.getCuotasDeSolicitud(solicitudCreditoV);
        System.out.println(cuotas.toString());


    }
}
