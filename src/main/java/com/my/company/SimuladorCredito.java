package com.my.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimuladorCredito {

    //Para este tipo de situaciones lo mas aplicativo es solicitrar por ID, no por usuario, por Solicitud
    //Sino simplemente por el ID de cada una
    private Map <Integer, List<SolicitudCredito>> listaSolicitudes;
    private Map<Integer, List<Cuota>> listaCuotas;

    public SimuladorCredito(){
        this.listaSolicitudes = new HashMap<>();
        this.listaCuotas = new HashMap<>();
    }

    //Guardar la solicitud asociada al usuario
    public void guardarSolicitud(Usuario u, SolicitudCredito s){
        validarCampoUsuario(u);
        validarCampoSolicitud(s);

        if (listaSolicitudes.containsKey(u.getId())) {
            listaSolicitudes.get(u.getId()).add(s);
        } else {
            List<SolicitudCredito> solicitudes = new ArrayList<>();
            solicitudes.add(s);
            listaSolicitudes.put(u.getId(), solicitudes);
        }
        generarCuotasASolicitud(s);
    }

    public List<Cuota> getCuotasDeSolicitud(SolicitudCredito s){
        return listaCuotas.get(s.getId());
    }



    private void generarCuotasASolicitud(SolicitudCredito s){
        validarCampoSolicitud(s);
        LocalDate inicio = s.getFechaInicio();
        int cuotas = s.getCantidadCuotas();

        for (int i = 1; i <= cuotas; i++) {
            Cuota c = new Cuota(calcularMontoCuotas(s.getCantidadPrestada(), s.getCantidadCuotas()), inicio.plusMonths(i), "ACTIVO", s);
            if (listaCuotas.containsKey(s.getId())){
                listaCuotas.get(s.getId()).add(c);
            } else {
                List<Cuota> cuota = new ArrayList<>();
                cuota.add(c);
                listaCuotas.put(s.getId(), cuota);
            }
        }
    }

    private BigDecimal calcularMontoCuotas(BigDecimal monto, int cuota){
        return monto.divide(new BigDecimal(cuota), 2 , RoundingMode.HALF_UP);
    }

    //Devolver las solicitudes asociadas al usuario
    public List<SolicitudCredito> getSolicitudesAsociadasUsuario(Usuario usuario){
        return listaSolicitudes.get(usuario.getId());
    }



    private void validarCampoSolicitud(SolicitudCredito s){
        if (s == null){
            throw new IllegalArgumentException("El campo solicitud no puede estar vacio");
        }
    }

    private void validarCampoUsuario(Usuario usuario){
        if (usuario == null){
            throw new IllegalArgumentException("El campo usuario no puede estar vacio");
        }
    }


}
