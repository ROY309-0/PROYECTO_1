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

    //Inicializacion del constructor
    public SimuladorCredito(){
        this.listaSolicitudes = new HashMap<>();
        this.listaCuotas = new HashMap<>();
    }

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

    //Consultamos las cuotass de la solicitud por medio de la key
    public List<Cuota> getCuotasDeSolicitud(SolicitudCredito s){
        return listaCuotas.get(s.getId());
    }

    //Metodo privado para generar las cuotas de una solicitud
    private void generarCuotasASolicitud(SolicitudCredito s){
        validarCampoSolicitud(s);
        //Obtenemos la fecha de inicio de la solicitud para sumar los meses y obtenemo las cantidades de cuotas
        LocalDate inicio = s.getFechaInicio();
        int cuotas = s.getCantidadCuotas();

        //Recorremos con un bucle con la longitud de las cuotas para generarlas cada vuelta
        for (int i = 1; i <= cuotas; i++) {
            //A cada vuelta construimos una cuota nueva, en donde desde la fecha de inicio vamos sumando
            //los meses con plusMonths
            Cuota c = new Cuota(calcularMontoCuotas(s.getCantidadPrestada(), s.getCantidadCuotas()), inicio.plusMonths(i), s);
            //Ahora usamos la misma logica de si ya existe la lista
            if (listaCuotas.containsKey(s.getId())){
                listaCuotas.get(s.getId()).add(c);
                //Y si no, la creamos
            } else {
                List<Cuota> cuota = new ArrayList<>();
                cuota.add(c);
                listaCuotas.put(s.getId(), cuota);
            }
        }
    }

    //Para obtener las cuotas vencidas tenemos que recibir una solicitud
    public List<Cuota> getCuotasVencidasSolicitud(SolicitudCredito s){
        validarCampoSolicitud(s);
        //Establecemos la fecha
        LocalDate hoy = LocalDate.of(2025, 12, 1);

        //Creamos una lista para obtener las cuotas de la lista por medio de la key de la solicitud
        List<Cuota> cuotas = listaCuotas.get(s.getId());
        //Creamos una lista para poder separar las vencidas de las que no lo estan
        List<Cuota> cuotasVencidas = new ArrayList<>();
        //Recorremos con un foreach la lista usando un objeto del mismo tipo de la lista
        for (Cuota r : cuotas){
            //Obtener la fecha de vencimiento del objeto cuota
            //Si la fecha de vencimiento es antes a la fecha de hoy en la lista de cuotas vencidas se añadira
            if (r.getFechaVencimiento().isBefore(hoy)){
                cuotasVencidas.add(r);
            }
        }
        //Retornas las cuotas que ya estan vencidas
        return cuotasVencidas;
    }

    //Con este metodo se paga la cuota y cambia su estado
    public void pagarCuota(SolicitudCredito s, int id){
        //Valido la entrada del campo solicitud
        validarCampoSolicitud(s);
        //Valido el id de la solicitud
        validarId(id);

        List<Cuota> cuotasList = listaCuotas.get(s.getId());
        boolean idEncontrado = false;
        for (Cuota c : cuotasList){
            if (c.getId() == id){
                verificarEstadoDeCuota(c);
                c.Pagar();
                idEncontrado = true;
            }
        }

        if (!idEncontrado){
            throw new IllegalArgumentException("La cuota no fue encontrada. Por favor verificar la entrada");
        }
    }

    public List<Cuota> getCuotasPendientesSolicitud(SolicitudCredito s){
        validarCampoSolicitud(s);

        //Establecemos la fecha actual
        LocalDate fecha = LocalDate.of(2024, 6, 1);

        //Creamos una lista para obtener las cuotas por medio del key id de solicitud
        List<Cuota> cuotasLista = listaCuotas.get(s.getId());
        //Creamos una lista para separar las pendientes
        List<Cuota> cuotasPendientes = new ArrayList<>();
        //Recorremos con un foreach la lista que contiene las cuotas
        for (Cuota r: cuotasLista){
            //Si la fecha de vencimiento de la cuota es despues a la fecha actual esta pendiente
            if (r.getFechaVencimiento().isAfter(fecha) || r.getFechaVencimiento().isEqual(fecha)){
                cuotasPendientes.add(r);
            }
        }

        //Retornar las cuotaspendientes
        return cuotasPendientes;
    }

    //Devolver las solicitudes asociadas al usuario
    public List<SolicitudCredito> getSolicitudesAsociadasUsuario(Usuario usuario){
        return listaSolicitudes.get(usuario.getId());
    }

    //Calculamos el monto de las cuota para no hacerlo manual cada vez
    private BigDecimal calcularMontoCuotas(BigDecimal monto, int cuota){
        //Me ayuda a dividir entre bigdecimal para que no hayan infinitos (en ocaciones) y redondear a dos
        return monto.divide(BigDecimal.valueOf(cuota), 2 , RoundingMode.HALF_UP);
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

    private void validarCampoCuota(Cuota cuota){
        if (cuota == null){
            throw new IllegalArgumentException("El campo cuota no puede estar vacio");
        }
    }

    private void verificarEstadoDeCuota(Cuota c){
        if (c.getEstado() == EstadoCuota.PAGADA){
            throw new IllegalStateException("La cuota ya esta pagada");
        }

        if (c.getEstado() == EstadoCuota.VENCIDA){
            throw new IllegalStateException("La cuota ya esta vencida");
        }
    }

    private void validarId(int id){
        if (id == 0){
            throw new IllegalArgumentException("La cuota ingresada no puede ser cero");
        }

        if (id < 0){
            throw new IllegalArgumentException("La cuota no puede ser negativa");
        }
    }
}
