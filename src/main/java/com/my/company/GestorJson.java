package com.my.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
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


    public void guardarUsuarioJs(Map<Integer, Usuario> usuarios){
        try {
            File carpeta = new File("data/json");
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }

            objectMapper.writeValue(new File("data/json/usuario.json"), usuarios);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map<Integer, Usuario> cargarUsuarios(){
        try{
            return objectMapper.readValue(new File("data/json/usuario.json"), new TypeReference<Map<Integer, Usuario>>() {
            });
        } catch (IOException e){
            e.printStackTrace();
            return new HashMap<>();
        }
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
            //TypeReference le da a Jackson los tipos correctos para construir el objeto
            return objectMapper.readValue(new File("data/json/solicitudcredito.json"), new TypeReference<Map<Integer, List<SolicitudCredito>>>() {
            });
        } catch (IOException e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /*CUOTAS*/
    public void guardarCuotas(Map<Integer, List<Cuota>> cuotas){
        try {

            File carpeta = new File("data/json");
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }
            objectMapper.writeValue(new File("data/json/cuota.json"), cuotas);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map<Integer, List<Cuota>> cargarCuotas(){
        try {

            //TypeReference le da a Jackson los tipos correctos para construir el objeto
            return objectMapper.readValue(new File("data/json/cuota.json"), new TypeReference<Map<Integer, List<Cuota>>>() {
            });
        } catch (Exception e){
           e.printStackTrace();
           return new HashMap<>();
        }
    }

}
