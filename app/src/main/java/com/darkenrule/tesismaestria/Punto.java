package com.darkenrule.tesismaestria;

/**
 * Created by Fernando on 29/10/2015.
 */
public class Punto {

    private double latitud;
    private double longitud;

    public Punto(double longitud, double latitud){
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Punto(){

    }


    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
