package com.my.company.TEST;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.company.SolicitudCredito;
import com.my.company.Usuario;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PruebaJsonSolicitudCredito {
    public static void main(String[] args) {
        //Creamos el objeto utilizando el ObjecMapper
        ObjectMapper objectMapperS = new ObjectMapper();
        objectMapperS.registerModule(new JavaTimeModule());//Este metodo me permite registrar modulos para poder trabajar sin problema con datos

        //Para poder asociar mi usuario tengo que leer el JSON y volver a convertirlo en objeto
        //Ya que la anotacion me ayudara a ignorar que guarde to do el usuario asociado, solo sera el id
        try {
            Usuario usuarioAJ = objectMapperS.readValue(new File("data/json/usuario.json"), Usuario.class);
            System.out.println("USUARIO LEIDO CORRECTAMENTE");
            System.out.println(usuarioAJ.toString());
            //Creamos la solicitud y asociamos
            BigDecimal prestamo2 = new BigDecimal("1200.00");
            BigDecimal interesPrestamo2 = new BigDecimal("12.00");
            LocalDate fechaI2 = LocalDate.of(2024, 12, 21);
            LocalDate fechaF2 = LocalDate.of(2025, 12, 21);
            SolicitudCredito solicitudCredito = new SolicitudCredito(prestamo2,  fechaI2 , fechaF2, interesPrestamo2, 24, usuarioAJ);

            //Ahora si creamos la carpeta
            File carpeta = new File("data/json");
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }

            objectMapperS.writeValue(new File("data/json/solicitudcredito.json"), solicitudCredito);
            System.out.println("JSON CREADO CORRECTAMENTE");
            System.out.println(solicitudCredito.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
