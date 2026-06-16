package com.my.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorCuotas {
    //Mantenemos la misma indicación de usar el ID como clave
    private Map<Integer, List<Cuota>> listaCuotas;

    //Ahora inicializamos el constructor de la clase  creando el HashMap.
    public GestorCuotas(){
        this.listaCuotas = new HashMap<>();
    }

    /*Metodos que hacen algo*/

    //Consultamos las cuotas de la solicitud por medio la key, que en este caso sería el ID
    public List<Cuota> getCuotasDeSolicitud(SolicitudCredito s){
        //retorna la lista de cuotas de la solicutud. Como ya establecimos que la key sera el ID de la solicitud, lo obtenemos mediante el.
        return listaCuotas.get(s.getId());
    }

    //Este metodo privado me genera las cuotas, recibiendo una SolicitudCredito
    private void generarCuotasASolicitud(SolicitudCredito s){
        validarCampoSolicitud(s);
        //Obtenemos la fecha de inicio de la solicitud para sumar los meses y obtenemo las cantidades de cuotas
        LocalDate inicio = s.getFechaInicio();
        //La variable cuotas obtiene la cantidad de cuotas desde la solicitud, en SolicitudCredito hay unn metodo para obtenerlas
        //Entonces al obtenerlas devuelve un entero y se guarda en la variable cuotas.
        int cuotas = s.getCantidadCuotas();

        //Recorremos con un bucle con la longitud de las cuotas que previamente obtuvimos de la solicitud, para generarlas cada vuelta
        for (int i = 1; i <= cuotas; i++) {
            //A cada vuelta construimos una cuota nueva, en donde desde la fecha de inicio vamos sumando
            //los meses con plusMonths
            Cuota c = new Cuota(calcularMontoCuotas(s.getCantidadPrestada(), s.getCantidadCuotas()), inicio.plusMonths(i), s);
            //Ahora usamos la misma logica de si ya existe la lista
            //Si ya existe una lista que contiene la key de la solicitud de las cuotas, entonces
            //Simplemente vamos introduciendo cada cuota nueva a ella
            if (listaCuotas.containsKey(s.getId())){
                listaCuotas.get(s.getId()).add(c);
                //Y si no encontramos la key, entonces creamos una lista nueva donde almacenarlas
            } else {
                //Creamos la lista nueva
                List<Cuota> cuota = new ArrayList<>();
                //Añadismos la cuota a la lista
                cuota.add(c);
                //Usando la key de la Solicitud, guardamos la lista en el map
                listaCuotas.put(s.getId(), cuota);
            }
        }
    }

    //Metodo que obtiene las cuotas vencidas de la solicitud
    public List<Cuota> getCuotasVencidasSolicitud(SolicitudCredito s){
        validarCampoSolicitud(s);
        //Establecemos la fecha para pruebas - ERROR CRITICO EN PRODUCCION SI SE ESTABLECE
        //LocalDate hoy = LocalDate.of(2025, 12, 1);
        LocalDate hoy = LocalDate.now();

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

    //Me permite obtener las cuotas pendientes de la solicitud que aun no se han pagado
    //Recibe como parametro una solicitud de credito con la cual accedemos al id de la lista que contiene las cuotas de esa solicitud
    public List<Cuota> getCuotasPendientesSolicitud(SolicitudCredito s){
        validarCampoSolicitud(s);

        //Establecemos la fecha actual - usando LocalDate
        LocalDate fecha = LocalDate.now();

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

    //Con este metodo se paga la cuota y cambia su estado
    public void pagarCuota(SolicitudCredito s, int id){
        //Valido la entrada del campo solicitud
        validarCampoSolicitud(s);
        validarId(id);
        //Obtengo la lista de cuotas que necesito por medio del Id.
        List<Cuota> cuotasList = listaCuotas.get(s.getId());
        boolean idEncontrado = false;
        //Con un foreach recorro la lista que necesito y la busco por el id de la cuota
        for (Cuota c : cuotasList){
            //Si encuento el id verifico el estado de la cuota y luego lo pago y lo marco encontrado
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


    /*Privados*/
    //Solo para validar objetos de tipo SolicitudCredito
    private void validarCampoSolicitud(SolicitudCredito s){
        if (s == null) {
            throw new IllegalArgumentException("El campo solicitud no puede estar vacío");
        }
    }

    //Solo para validar el Id
    private void validarId(int id){
        if (id <= 0){
            throw new IllegalArgumentException("La cuota ingresada no puede ser cero o vacia");
        }
    }

    //Solo para validar el estado de la cuota dependiendo de como se encuentre
    private void verificarEstadoDeCuota(Cuota c){
        if (c.getEstado() == EstadoCuota.PAGADA){
            throw new IllegalStateException("La cuota ya esta pagada");
        }

        if (c.getEstado() == EstadoCuota.VENCIDA){
            throw new IllegalArgumentException("La cuota ya está vencida");
        }
    }

    //Calcular el monto de las cuotas para no hacerlo manual cada vez
    private BigDecimal calcularMontoCuotas(BigDecimal monto, int cuota){
        //Me ayuda a dividir entre bigdecimal para que no hayan infinitos (en ocaciones) ademas la constante lo redondea a dos
        return monto.divide(BigDecimal.valueOf(cuota), 2, RoundingMode.HALF_UP);
    }


}
