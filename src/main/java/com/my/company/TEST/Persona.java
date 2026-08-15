package com.my.company.TEST;

public class Persona {

    //Los atributos son publicos cualquiera pueda acceder a ellos
    public String nombre;
    public int edad;

    String texto = "CARLOS123";
    //El metodo matcher indica si una cadena de texto coincide con la expresion regular especificada dentro del metodo
    boolean esValido = texto.matches("[a-zA-Z0-9]+"); //Expresion regular que verifica si hay minusculas, mayusculas y al menos un numero

    public static void main(String[] args) {

        String texto = "carlos 1234!";
        //El metodo matcher indica si una cadena de texto coincide con la expresion regular especificada dentro del metodo
        boolean esValido = texto.matches("[a-zA-Z0-9]+"); //Expresion regular que verifica si hay minusculas, mayusculas y al menos un numero
        System.out.println(esValido);
    }
}
