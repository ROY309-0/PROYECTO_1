package com.my.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GestorJson {
    private ObjectMapper objectMapper = new ObjectMapper();

    public GestorJson(){
        objectMapper.registerModule(new JavaTimeModule());
    }

    /*SOLICITUDES*/
    public void guardarSolicitudJs(Map<Integer, List<SolicitudCredito>> solicitudes){
        try {
            File carpeta = new File("data/json");
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }

            objectMapper.writeValue(new File("data/json/solicitudcredito.json"), solicitudes);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Carga las solicitudes para poder trabajar con ellas.
    public Map<Integer, List<SolicitudCredito>> cargarSolicitudes(){
        try{
            return objectMapper.readValue(new File("data/json/solicitudcredito.json"), Map.class);
        } catch (IOException e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /*CUOTAS*/
    public void guardarCuotas(Map<Integer, List<Cuota>> cuotas){
        try {
            //Recuperamos la solicitud

            //s = objectMapper.readValue(new File("data/json/solicitudcredito.json"), SolicitudCredito.class);
            File carpeta = new File("data/json");
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }
            objectMapper.writeValue(new File("data/json/cuota.json"), cuotas);

        }catch (IOException e){
            e.printStackTrace();
        }
    }












    
    
    

}
