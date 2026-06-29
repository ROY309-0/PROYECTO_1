package com.my.company.TEST;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.my.company.Cuota;
import com.my.company.SolicitudCredito;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PruebaJsonCuota {
    public static void main(String[] args) {
        ObjectMapper objectMapperC = new ObjectMapper();
        objectMapperC.registerModule(new JavaTimeModule());

        try{
            //Primero tengo que leer la solicitud que tengo en el JSON y traerla a objeto Java de nuevo
            SolicitudCredito solicitudCreditoJ = objectMapperC.readValue(new File("data/json/solicitudcredito.json"), SolicitudCredito.class);
            System.out.println("SOLICITUD LEIDO CORRECTAMENTE");
            System.out.println(solicitudCreditoJ.toString());

            //Creamos la cuota y asociamos
            BigDecimal montoCuota = new BigDecimal("123.00");
            LocalDate fechaVencimiento = LocalDate.of(2025, 1, 21);
            Cuota cuotaJ = new Cuota(montoCuota, fechaVencimiento, solicitudCreditoJ);

            //Creamos las rutas necesarias
            File carpeta = new File("data/json/");
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }

            //creamos el archivo
            objectMapperC.writeValue(new File("data/json/cuota.json"), cuotaJ);
            System.out.println("JSON CREADO CORRECTAMENTE");
            System.out.println(cuotaJ.toString());

            //leer el archivo - TEST
            objectMapperC.readValue(new File("data/json/cuota.json"), Cuota.class);
            System.out.println("CUOTA LEIDA CORRECTAMENTE");
            System.out.println(cuotaJ.toString());
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
