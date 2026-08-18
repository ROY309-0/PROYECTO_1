package com.my.company.TEST.PERSISTENCIA;
import com.my.company.Cuota;
import com.my.company.SimuladorCredito;
import com.my.company.SolicitudCredito;
import com.my.company.Usuario;
import java.util.List;

public class Prueba02CargarYModificar {
    public static void main(String[] args) {
        /*
        1. Crear solamente un nuevo SimuladorCredito.
        2. No crear usuarios, solicitudes ni cuotas manualmente.
        3. Ejecutar tu proceso de carga.
        4. Intentar usar los métodos normales del simulador:
            Buscar usuario 1.
            Consultar sus solicitudes.
            Consultar las cuotas.
        5. Confirmar que la cuota 1 sigue pagada.
        6. Pagar otra cuota.
        7. Guardar nuevamente.
        * */

        //Crear SimuladorCredito
        SimuladorCredito simuladorCredito = new SimuladorCredito();
        //No crear usuarios, solicitudes, ni cuotas manualmente
        //Ejecutar proceso de carga
        simuladorCredito.cargar();

        //Usar metodos del simulador
        Usuario usuario = simuladorCredito.buscarUsuario(1);
        System.out.println("Usuario encontrado: " + usuario);

        List<SolicitudCredito> solicitudes = simuladorCredito.getSolicitudesAsociadasUsuario(usuario);
        System.out.println("Solicitudes: "+ solicitudes);
        SolicitudCredito solicitudCredito = solicitudes.get(0);

        List<Cuota> cuotas = simuladorCredito.getCuotasDeSolicitud(solicitudCredito);
        System.out.println("Cuotas: " + cuotas);
        //Confirmar cuota 1
        System.out.println(simuladorCredito.getCuotasDeSolicitud(solicitudCredito));
        //Pagar otra cuota
        simuladorCredito.pagarCuota(solicitudCredito, 4);
        //Guardar
        simuladorCredito.guardar();
        System.out.println(simuladorCredito.getCuotasDeSolicitud(solicitudCredito));


    }
}
