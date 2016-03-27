package com.darkenrule.tesismaestria;

import android.location.Location;

/**
 * Created by Fernando on 30/10/2015.
 */
public class PuntoModelo {

    private String name;
    private Location location;

    public PuntoModelo(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public PuntoModelo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
