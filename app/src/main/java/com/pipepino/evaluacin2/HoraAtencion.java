package com.pipepino.evaluacin2;

public class HoraAtencion {
    String id;
    String nombre;
    String duenio;
    String fecha;

    public HoraAtencion(){}

    public HoraAtencion(String id, String nombre, String duenio, String fecha){
        this.id=id;
        this.nombre=nombre;
        this.duenio=duenio;
        this.fecha=fecha;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
