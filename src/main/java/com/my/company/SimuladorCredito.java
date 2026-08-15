package com.my.company;


import java.util.List;
import java.util.Map;

public class SimuladorCredito {
    //Creamos dos objetos del tipo de las dos clases que usamos para poder acceder a los metodos
    private GestorUsuarios gestorUsuarios;
    private GestorSolicitudes gestorSolicitudes;
    private GestorCuotas gestorCuotas;
    private GestorJson gestorJson;

    //El constructor básicamente ya crea estas variables, no es necesario instanciarlas desde main
    public SimuladorCredito(){
        this.gestorUsuarios = new GestorUsuarios();
        this.gestorSolicitudes = new GestorSolicitudes();
        this.gestorCuotas = new GestorCuotas();
        this.gestorJson = new GestorJson();
    }

    /*Reconectar solicitudes con usuario*/
    public Map<Integer, List<SolicitudCredito>> cargarSolicitudesJs() {
        Map<Integer, Usuario> usuariosCargados = gestorUsuarios.obtenerMapaUsuarios();
        Map<Integer, List<SolicitudCredito>> solicitudesCargadas = gestorSolicitudes.obtenerMapaSolicitudes();

        for (Integer idUsuario: solicitudesCargadas.keySet()){
            Usuario usuarioE = usuariosCargados.get(idUsuario);
            List<SolicitudCredito> solicitudesE = solicitudesCargadas.get(idUsuario);

            for (SolicitudCredito s : solicitudesE){
                s.setUsuarioAsociado(usuarioE);
            }
        }

        return solicitudesCargadas;
    }

    /*Reconectar cuotas con solicitudes*/
    public Map<Integer, List<Cuota>> cargarCuotas(){
        Map<Integer, List<SolicitudCredito>> solicitudesCargadas = gestorSolicitudes.obtenerMapaSolicitudes();
        Map<Integer, List<Cuota>> cuotasCargadas = gestorCuotas.obtenerMapaCuotas();
        for (Integer idSolicitud: cuotasCargadas.keySet()){
            for (Integer idUsuario: solicitudesCargadas.keySet()){
                List<SolicitudCredito> solicitudCreditosE = solicitudesCargadas.get(idUsuario);
                List<Cuota> cuotasE = cuotasCargadas.get(idSolicitud);
                for (Cuota c : cuotasE){
                    for (SolicitudCredito s : solicitudCreditosE){
                        if (s.getId() == idSolicitud){
                            c.setSolicitudCreditoAsociada(s);
                        }
                    }
                }
            }
        }
        return cuotasCargadas;
    }

    /*USUARIOS*/
    public void guardarUsuario(Usuario u){
        gestorUsuarios.guardarUsuario(u);
    }

    public Usuario buscarUsuario(int id){
        return gestorUsuarios.buscarUsuario(id);
    }

    //Json usuarios
    public void guardarUsuarioJs(){
        gestorJson.guardarUsuarioJs(gestorUsuarios.obtenerMapaUsuarios());
    }

    public Map<Integer, Usuario> cargarUsuarios(){
        return gestorJson.cargarUsuarios();
    }

    //Me ayuda a cargar y reemplazar
    public void cargarMapaUsuarios(){
        gestorUsuarios.cargarMapaUsuarios(gestorJson.cargarUsuarios());
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

    public void guardarCuotasJss(){
        gestorJson.guardarCuotas(gestorCuotas.obtenerMapaCuotas());
    }

    public void aprobarSolicitud(SolicitudCredito s){
        gestorSolicitudes.aprobarSolicitud(s);
        gestorCuotas.generarCuotasASolicitud(s);
    }

    public void cancelarSolicitud(SolicitudCredito s){
        gestorSolicitudes.cancelarSolicitud(s);
    }

    public EstadoSolicitud estadoSolicitud(SolicitudCredito s){
        return gestorSolicitudes.estadoSolicitud(s);
    }

    public void cargarMapaSolicitudes(){
        gestorSolicitudes.cargarMapaSolicitudes(gestorJson.cargarSolicitudes());
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

    public void cargarMapaCuotas(){
        gestorCuotas.cargarMapaCuotas(gestorJson.cargarCuotas());
    }

    public void cargar(){
        //El constructor básicamente ya crea estas variables, no es necesario instanciarlas desde main
            cargarMapaUsuarios();
            cargarMapaSolicitudes();
            cargarMapaCuotas();
            cargarUsuarios();
            cargarSolicitudesJs();
            cargarCuotas();

    }

    public void guardar(){
        guardarUsuarioJs();
        guardarSolicitudJss();
        guardarCuotasJss();
    }
}
