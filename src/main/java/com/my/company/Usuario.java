package com.my.company;

public class Usuario {
    //Esta clase tiene los atributos privados pero a JackSON se le complicaria reconstruir el objeto desde JSON
    private int id;
    private static int contador = 0;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String direccion;
    private boolean isActive;//is false

    public Usuario(String nombreP, String apellidoP, String correoP, String celularP, String direccionP){
        validarCampo(nombreP, "El campo nombre no puede estar vacio");
        //Longitud de cadena
        validarLongitudCadena(nombreP, 30, 2, "Nombre");
        validarCampo(apellidoP, "El campo apellido no puede estar vacio");
        validarLongitudCadena(apellidoP, 50, 5, "Apellido");
        validarCampo(correoP, "El campo correo no puede estar vacio");
        validarCampo(celularP, "El campo celular no puede estar vacio");
        validarCampo(direccionP, "La direcccion no puede estar vacia");
        validarLongitudCadena(direccionP, 100, 10, "Direccion");
        this.id = generarId();
        this.nombre = nombreP.trim();
        this.apellido = apellidoP.trim();
        this.correo = correoP.trim();
        this.celular = celularP.trim();
        this.direccion = direccionP.trim();
        this.isActive = true;
    }

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public String getCorreo(){
        return correo;
    }

    public String getCelular(){
        return celular;
    }

    public String getDireccion(){
        return direccion;
    }

    public boolean isActive(){
        return isActive;
    }

    //validar campos
    private void validarCampo(String campo, String mensaje){
        if (campo == null || campo.isBlank()){
            throw new IllegalArgumentException(mensaje);
        }
    }

    //Para poder asegurar sin tener que repetir el mismo codigo se puede establecer un minimo y un maximo para evitar problemas
    //Necesito un campo donde almacenar el valor real, un limite de caracteres maximo, un minimo permitido, y un campo que me devuelva que tipo es
    private void validarLongitudCadena(String campo, int limiteMax, int limiteMin, String campoU){
        validarlimites(limiteMax);
        validarlimites(limiteMin);

        if (campo.length() > limiteMax){
            throw new IllegalArgumentException("El campo "+ campoU +" supera la cantidad de carateres permitidos");
        }

        if (campo.length() < limiteMin){
            throw new IllegalArgumentException("El campo " + campoU + " no puede tener pocos caracteres");
        }
    }

    //Generar Id
    private static int generarId(){
        return ++contador;
    }

    private void validarlimites(int n){
        if (n <=0){
            throw new IllegalArgumentException("El limite no puede ser cero o negativo");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", celular='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
