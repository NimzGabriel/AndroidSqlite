package com.ggomez.myapplicationsqlite.models;

public class Usuario {
    private int id;
    private String nombres;
    private String apellidos;
    private String email;
    private String clave;
    private String tipo;

    public Usuario() { }

    public Usuario(String nombres, String apellidos, String email, String clave, String tipo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.clave = clave;
        this.tipo = tipo;
    }

    public Usuario(int id, String nombres, String apellidos, String email, String clave, String tipo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.clave = clave;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Modify toString";
    }
}
