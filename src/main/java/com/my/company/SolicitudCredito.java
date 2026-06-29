package com.my.company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true) //si JackSON encuenta un campo que no conoce solo lo ignora, no lanza error
//La validación vive en la entidad
public class SolicitudCredito {
    private int id;
    private static int contador = 0;
    private BigDecimal cantidadPrestada;
    private EstadoSolicitud estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal interes;
    private int cantidadCuotas;
    private Usuario usuarioAsociado;

    public SolicitudCredito(){

    }

    public SolicitudCredito(BigDecimal cantidadPrestadaP, LocalDate fechaInicioP, LocalDate fechaFinP, BigDecimal interesP, int cantidadCuotasP, Usuario usuarioP){
        validarCampo(cantidadPrestadaP);
        validarCampo(fechaInicioP);
        validarCampo(fechaFinP);
        validarFecha(fechaFinP, fechaInicioP, "La fecha de vencimiento no puede ser la misma de inicio o anterior a ella");
        validarCampo(interesP);
        validarCampo(cantidadCuotasP, "La cantidad de cuotas no es correcta");
        validarCampo(usuarioP, "El usuario no puede estar vacio");

        this.id = generarId();
        this.cantidadPrestada = cantidadPrestadaP;
        this.estado = EstadoSolicitud.REVISION;
        this.fechaInicio = fechaInicioP;
        this.fechaFin = fechaFinP;
        this.interes = interesP;
        this.cantidadCuotas = cantidadCuotasP;
        this.usuarioAsociado = usuarioP;

    }

    public int getId(){
        return id;
    }

    public BigDecimal getCantidadPrestada(){
        return cantidadPrestada;
    }

    public EstadoSolicitud getEstado(){
        return estado;
    }

    public LocalDate getFechaInicio(){
        return fechaInicio;
    }

    public LocalDate getFechaFin(){
        return fechaFin;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    //No incluyas este getter al momento de crear el JSON
    @JsonIgnore
    public Usuario getUsuarioAsociado(){
        return usuarioAsociado;
    }

    //Este metodo es el que JackSON va a usar para regresarme solo el id del usuario asociado en vez de toda la informacion
    public int getIdUsuarioAsociado(){
        return  usuarioAsociado.getId();
    }
    //Lo mejor es siempre verificar el estado en la clase inicial para evitar redundancia de codigo en las siguientes clases.
    public void activarSolicitud(){
        if (this.estado != EstadoSolicitud.REVISION){
            throw new IllegalStateException("No se puede cambiar el estado");
        }
        this.estado = EstadoSolicitud.ACTIVA;
    }

    public void cancelarSolicitud(){
        if (this.estado != EstadoSolicitud.REVISION){
            throw new IllegalStateException("No se puede cambiar el estado");
        }
        this.estado = EstadoSolicitud.CANCELADA;
    }

    public void rechazarSolicitud(){
        if (this.estado != EstadoSolicitud.REVISION){
            throw new IllegalStateException("No se puede cambiar el estado");
        }
        this.estado = EstadoSolicitud.RECHAZADA;
    }

    private static int generarId(){
        return ++contador;
    }

    private void validarCampo(String campo, String mensaje){
        if (campo == null || campo.isBlank()){
            throw new IllegalArgumentException(mensaje);
        }
    }

    private void validarCampo(BigDecimal campo){

        if (campo == null){
            throw new IllegalArgumentException("El valor no puede estar vacio");
        }

        if(campo.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
    }

    private void validarCampo(LocalDate fecha){
        if (fecha == null){
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
    }

    private void validarFecha(LocalDate fechaF, LocalDate fechaI, String mensaje){
        if (fechaF.isBefore(fechaI) || fechaF.isEqual(fechaI)){
            throw new IllegalArgumentException(mensaje);
        }
    }

    private void validarCampo(Integer campo, String mensaje){
        if (campo == null || campo <= 0){
            throw new IllegalArgumentException(mensaje);
        }
    }

    public void validarCampo(Usuario u, String mensaje){
        if (u == null){
            throw new IllegalArgumentException(mensaje);
        }
    }

    //Setters temporales para poder construir el JSON y leerlo
    public void setId(int id){
        this.id = id;
    }

    public void setCantidadPrestada(BigDecimal cantidadPrestada){
        this.cantidadPrestada = cantidadPrestada;
    }

    public void setEstado(EstadoSolicitud estado){
        this.estado = estado;
    }

    public void setFechaInicio(LocalDate fechaInicio){
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin){
        this.fechaFin = fechaFin;
    }

    public void setInteres(BigDecimal interes){
        this.interes = interes;
    }

    public void setCantidadCuotas(int cantidadCuotas){
        this.cantidadCuotas = cantidadCuotas;
    }

    @JsonIgnore
    public void setUsuarioAsociado(Usuario usuarioAsociado){
        this.usuarioAsociado = usuarioAsociado;
    }

    @Override
    public String toString() {
        return "SolicitudCredito{" +
                "id=" + id +
                ", cantidadPrestada=" + cantidadPrestada +
                ", estado='" + estado + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", interes=" + interes +
                ", cantidadCuotas=" + cantidadCuotas +
                ", usuarioAsociado=" + usuarioAsociado +
                '}';
    }
}
