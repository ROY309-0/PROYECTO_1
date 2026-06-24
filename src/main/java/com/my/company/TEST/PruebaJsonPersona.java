package com.my.company.TEST;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.company.Usuario;

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
            //Antes de guardar cualquier archivo debemos crear un entorno en donde se pueda guardar ese archivo para ello usamos File
            File carpeta = new File("data/json"); //no crea la carpeta, solo representa la ruta
            if (!carpeta.exists()){//pregunta si la carpeta ya existe - boolean
                //carpeta.mkdir(); intenta crear la ultima carpeta
                carpeta.mkdirs(); //intenta crear la ruta necesaria de las carpetas
            }

            //Trabajaremos con File que puede lanzar excepciones de que la ruta no existe, o otro pograma esta usando el archivo
            //En este caso "new File"  solo representa la ruta, quien realmente intneta crear el archivo es el mapper.writeValue(...);
            //Java busca la ruta de PROYECTO_1

            mapper.writeValue(new File("data/json/persona.json"), p);
            System.out.println(System.getProperty("user.dir")); //Donde java esta interpretando las rutas relativas
            //intenta tomar el objeto p, convertirlo a JSON y escribir ese resultado en la ruta que le diste.
            // La conversión de Persona no falló; falló el acceso al lugar donde querías guardar el archivo.
            //Capturamos al excepcion e imprimos la pila de llamada
        } catch (IOException e){
            //El programa termino en codigo 0 porque se capturo la excepcion, diferente hubiera sido si no.
            e.printStackTrace();
        }

        //Leer el JSON y volverlo objeto
        try {
            //Este objeto no es el mismo que creamos inicialmente en memoria con "p"
            Persona pJson = mapper.readValue(new File("data/json/persona.json"), Persona.class);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
