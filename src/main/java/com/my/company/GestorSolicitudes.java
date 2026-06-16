package com.my.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorSolicitudes {
    //Para este tipo de situaciones lo mas aplicativo es solicitrar por ID, no por usuario, por Solicitud
    private Map<Integer, List<SolicitudCredito>> listaSolicitudes;

    public GestorSolicitudes(){
        this.listaSolicitudes = new HashMap<>();
    }

    /*Metodos que hacen algo*/

    //Guardar la solicitud asociada al usuario
    public void guardarSolicitud(Usuario u, SolicitudCredito s){
        validarCampoUsuario(u);
        validarCampoSolicitud(s);

        //Buscamos si en la lista de solicitudes esta la clave y si es asi añadimos esa solicitud a esa lista
        if (listaSolicitudes.containsKey(u.getId())) {
            listaSolicitudes.get(u.getId()).add(s);
            //Si la key no esta en la lista creamos una lista nueva
        } else {
            List<SolicitudCredito> solicitudes = new ArrayList<>();
            solicitudes.add(s);
            listaSolicitudes.put(u.getId(), solicitudes);
        }
        //Inicialmente la logica decia que convenia dejarlo aca, pero la responsabilidad no es de la solicitud
        //Llamamos al metodo para generar cuotas y luego las consultamos
        //generarCuotasASolicitud(s);
    }

    /*Metodos privados*/
    private void validarCampoSolicitud(SolicitudCredito s){
        if (s == null){
            throw new IllegalArgumentException("El campo solicitud no puede estar vacío");
        }
    }

    private void validarCampoUsuario(Usuario u){

    }





}
