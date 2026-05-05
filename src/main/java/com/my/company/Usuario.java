package com.my.company;

public class Usuario {
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
        validarCampo(apellidoP, "El campo apellido no puede estar vacio");
        validarCampo(correoP, "El campo correo no puede estar vacio");
        validarCampo(celularP, "El campo celular no puede estar vacio");
        validarCampo(direccionP, "La direcccion no puede estar vacia");
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

    //Generar Id
    private static int generarId(){
        return ++contador;
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
