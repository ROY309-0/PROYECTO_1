package com.my.company;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuota {
    private int id;
    private static int contador = 0;
    private BigDecimal montoCuota;
    private LocalDate fechaVencimiento;
    private String estado;
    private SolicitudCredito solicitudCreditoAsociada;

    public Cuota(BigDecimal montoCuotaP, LocalDate fechaVencimientoP, String estadoP, SolicitudCredito solicitudCreditoAsociadaP){
        validarCampo(montoCuotaP);
        validarCampo(fechaVencimientoP);
        validarFecha(fechaVencimientoP, solicitudCreditoAsociadaP);
        validarCampo(estadoP);
        validarCampo(solicitudCreditoAsociadaP);

        this.id = generarId();
        this.montoCuota = montoCuotaP;
        this.fechaVencimiento = fechaVencimientoP;
        this.estado = estadoP;
        this.solicitudCreditoAsociada = solicitudCreditoAsociadaP;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getMontoCuota() {
        return montoCuota;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    private void validarCampo(BigDecimal campo){
        if (campo == null){
            throw new IllegalArgumentException("El monto no puede estar vacio");
        }

        if (campo.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
    }

    private void validarCampo(LocalDate campo){
        if(campo == null){
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
    }

    private void validarFecha(LocalDate campo, SolicitudCredito s){
        if (campo.isEqual(s.getFechaInicio())){
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser la misma fecha de inicio de la solicitud");
        }

        if (campo.isBefore(s.getFechaInicio())){
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha de inicio de la solicitud");
        }

        if (campo.isAfter(s.getFechaFin())){
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser despues a la fehca de vencimiento de la solicitud");
        }
    }

    private void validarCampo(String campo){
        if (campo == null || campo.isBlank()){
            throw new IllegalArgumentException("El estado no puede estar vacio");
        }
    }

    private void validarCampo(SolicitudCredito s){
        if (s == null){
            throw new IllegalArgumentException("La solicitud asociada no puede estar vacia");
        }
    }

    private static int generarId(){
        return ++contador;
    }

    @Override
    public String toString() {
        return "Cuota{" +
                "Id=" + id +
                ", montoCuota=" + montoCuota +
                ", fechaVencimiento=" + fechaVencimiento +
                ", estado='" + estado + '\'' +
                ", solicitudCreditoAsociada=" + solicitudCreditoAsociada +
                '}';
    }
}
