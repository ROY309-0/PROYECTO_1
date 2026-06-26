package com.my.company.TEST;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.company.Usuario;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PruebasJsonUsuario {
    public static void main(String[] args) {
        //Creacion del objeto que permitira convertir el archivo Java a JSON
        ObjectMapper objectMapper = new ObjectMapper();

        //Creacion de Usuario
        Usuario user1 = new Usuario("Daniela", "Cruz", "danielacruz@test.com", "7890-1234", "Colonia El Carmen, Chalatenango");

        System.out.println("CONVERTIR OBJETO A JSON");
        /*CONVERTIR EL USUARIO DE ARCHIVO JAVA A JSON*/
        //Usamos un try-catch y convertimos el archivo Java a JSON
        try{
            //Antes de to do primero debemos asegurarnos de crear un entorno donde guardarlo
            File carpeta = new File("data/json"); //No se crea, solo representa la ruta
            if (!carpeta.exists()){
                carpeta.mkdirs(); //intenta crear la ruta necesaria de caep
            }

            //Aca es donde se crea el JSON
            //Toma el objeto, lo convierte en JSON y escribe ese resultado en la ruta especificada

            objectMapper.writeValue(new File("data/json/usuario.json"), user1);
            System.out.println("JSON creado correctamente");
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("LEER JSON Y CONVERTIR A OBJETO");
        /*LEER EL JSON Y VOLVERLO OBJETO DE NUEVO*/
        try{
            Usuario user1J = objectMapper.readValue(new File("data/json/usuario.json"), Usuario.class);
            System.out.println("JSON leido y Usuario convertido en objeto");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
