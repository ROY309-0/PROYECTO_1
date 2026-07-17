package com.my.company;

import java.util.List;
import java.util.Map;

public class SimuladorCredito {
    //Creamos dos objetos del tipo de las dos clases que usamos para poder acceder a los metodos
    private GestorSolicitudes gestorSolicitudes;
    private GestorCuotas gestorCuotas;
    private GestorJson gestorJson;

    //El constructor básicamente ya crea estas variables, no es necesario instanciarlas desde main
    public SimuladorCredito(){
        this.gestorSolicitudes = new GestorSolicitudes();
        this.gestorCuotas = new GestorCuotas();
        this.gestorJson = new GestorJson();
    }

    //Ahora creamos los metodos que necesita simulador credito para simularlos
    /*SOLICITUDES*/
    public void guardarSolicitud(Usuario u, SolicitudCredito s){
        gestorSolicitudes.guardarSolicitud(u, s);
    }

    public List<SolicitudCredito> getSolicitudesAsociadasUsuario(Usuario u){
        return gestorSolicitudes.getSolicitudesAsociadasUsuario(u);
    }

    //Guardar la informacion en el Json
    public void guardarSolicitudJss(){
        gestorJson.guardarSolicitudJs(gestorSolicitudes.obtenerMapaSolicitudes());
    }

    /*CUOTAS*/
    public List<Cuota> getCuotasDeSolicitud(SolicitudCredito s){
        return gestorCuotas.getCuotasDeSolicitud(s);
    }

    public List<Cuota> getCuotasVencidasSolicitud(SolicitudCredito s){
        return gestorCuotas.getCuotasVencidasSolicitud(s);
    }

    public List<Cuota> getCuotasPendientesSolicitud(SolicitudCredito s){
        return gestorCuotas.getCuotasPendientesSolicitud(s);
    }

    public void pagarCuota(SolicitudCredito s, int id){
        gestorCuotas.pagarCuota(s, id);
    }

    public void aprobarSolicitud(SolicitudCredito s){
        gestorSolicitudes.aprobarSolicitud(s);
        gestorCuotas.generarCuotasASolicitud(s);
    }

    public EstadoSolicitud estadoSolicitud(SolicitudCredito s){
        return gestorSolicitudes.estadoSolicitud(s);
    }
}
