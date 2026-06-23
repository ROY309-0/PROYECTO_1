package com.my.company.TEST;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PruebaJsonPersona {
    public static void main(String[] args) {
        //Creamos el objeto que permitirá convertir los archivos Java a JSON
        ObjectMapper mapper = new ObjectMapper();

        //Instanciamos la clase Persona
        Persona p = new Persona();
        p.nombre = "Juan";
        p.edad = 18;

        //Ahora convertimos ese objeto en el archivo JSON - Usamos try-catch ya que generará una excepcion
        try {
            //Trabajaremos con File que puede lanzar excepciones de que la ruta no existe, o otro pograma esta usando el archivo
            //En este caso "new File"  solo representa la ruta, quien realmente intneta crear el archivo es el mapper.writeValue(...);
            mapper.writeValue(new File("src/main/resource/persona.json"), p);
            //intenta tomar el objeto p, convertirlo a JSON y escribir ese resultado en la ruta que le diste.
            // La conversión de Persona no falló; falló el acceso al lugar donde querías guardar el archivo.
            //Capturamos al excepcion e imprimos la pila de llamada
        } catch (IOException e){
            //El programa termino en codigo 0 porque se capturo la excepcion, diferente hubiera sido si no.
            e.printStackTrace();
        }
    }
}
