package com.my.company.TEST.PERSISTENCIA;
import com.my.company.*;
import java.util.List;

public class Prueba03Verificar {
    public static void main(String[] args) {
        // 1. Crear un simulador nuevo
        SimuladorCredito simuladorCreditoVerificar = new SimuladorCredito();
        // 2. Cargar el sistema
        simuladorCreditoVerificar.cargar();
        // 3. Buscar al usuario 1
        Usuario usuarioV = simuladorCreditoVerificar.buscarUsuario(1);
        //System.out.println("Usuario encontrado: "+ usuarioV.toString());
        // 4. Consultar sus solicitudes
        List<SolicitudCredito> solicitudes = simuladorCreditoVerificar.getSolicitudesAsociadasUsuario(usuarioV);
        //System.out.println(solicitudes.toString());
        // 5. Tomar la solicitud utilizada en las pruebas
        SolicitudCredito solicitudCreditoV = solicitudes.get(0);
        // 6. Consultar sus cuotas
        List<Cuota> cuotas = simuladorCreditoVerificar.getCuotasDeSolicitud(solicitudCreditoV);
        //System.out.println(cuotas.toString());


        if (solicitudCreditoV.getUsuarioAsociado() == null) {
            throw new IllegalStateException("La solicitud no tiene usuario asociado");
        } else {
            System.out.println("La solicitud esta asociada");
        }

        if (solicitudCreditoV.getIdUsuarioAsociado() == usuarioV.getId()){
            System.out.println("La solicitud esta asociada al usuario: " + usuarioV.toString());
        } else {
            throw new IllegalStateException("La solicitud no esta asociada a ningun usuario");
        }

        //Cuotas
        for (Cuota c: cuotas){
            if (c.getId() == 1 || c.getId() == 4){
                if (c.getEstado() != EstadoCuota.PAGADA){
                    throw new IllegalStateException("La cuota esta pendiente");
                }

            } else {
                if (c.getEstado() != EstadoCuota.PENDIENTE){
                    throw new IllegalStateException("La cuota no esta pagada ni pendiente");
                }

            }
            if (c.getSolicitudCreditoAsociada() == null){
                throw new IllegalStateException("La solicitud es null");
            }
            System.out.println(c);
        }
        System.out.println("PRUEBA DE PERSISTENCIA SUPERADA");


    }
}
