package com.my.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorJson {
    ObjectMapper objectMapper = new ObjectMapper();

    public GestorJson(){
        objectMapper.registerModule(new JavaTimeModule());
    }

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






    
    
    

}
