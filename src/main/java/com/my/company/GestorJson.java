package com.my.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorJson {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<Integer, List<SolicitudCredito>> guardarSolicitudes;



    public GestorJson(){
        objectMapper.registerModule(new JavaTimeModule());
        this.guardarSolicitudes = new HashMap<>();
    }

    public void guardarSolicitud()






    
    
    

}
