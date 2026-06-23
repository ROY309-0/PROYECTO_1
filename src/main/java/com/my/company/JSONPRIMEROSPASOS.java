package com.my.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONPRIMEROSPASOS {
    public static void main(String[] args) {
        //Esta es la herramienta que convierte los archivos Java a JSON
        ObjectMapper mapper = new ObjectMapper();
        Usuario miUsuario = new Usuario("Carlos", "Ramirez", "carlos.ramirez@test.com", "7234-6789", "Avenida Los Pinos #45,Santa Ana");

        //Para convertir un objeto a JSON y escribirlo en un archivo
        try{
            //Convierte miUsuario en una archivo con la estructura JSON y lo guarda en el archivo que le dimos nombre
            mapper.writeValue(new File("usuario.json"), miUsuario);
        } catch (IOException e){
            System.out.println(e);
        }

        //Para leer el archivo JSON y convertirlo de nuevo en un objeto
        //Voy a crear un usuario para mappear el objeto JSON especificando la ruta donde esta y de que clase viene
        try{
            Usuario u = mapper.readValue(new File("usuario.json"), Usuario.class);
        } catch (IOException e){
            System.out.println(e);
        }

        //Para visualizar en la consola el archivo JSON
        try {
            String json = mapper.writeValueAsString(miUsuario);
            System.out.println(json);
        } catch (IOException e){
            System.out.println(e);
        }



    }
}
