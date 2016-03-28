package com.darkenrule.tesismaestria;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class CarteleraActivity extends Activity {

    boolean primero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);
        TextView textView = (TextView)findViewById(R.id.textCartelera);
        textView.setText(recuperarPreferencias());
    }

    public String recuperarPreferencias()
    {
        String cadena = "";

        SharedPreferences preferences = getSharedPreferences("com.darkenrule.tesismaestria_preferences",
                MODE_PRIVATE);

        boolean comprobar;

        comprobar = preferences.getBoolean("action", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 1 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 1 ";
        }

        comprobar = preferences.getBoolean("adventure", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 2 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 2 ";
        }

        comprobar = preferences.getBoolean("animation", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 3 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 3 ";
        }

        comprobar = preferences.getBoolean("biography", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 4 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 4 ";
        }

        comprobar = preferences.getBoolean("comedy", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 5 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 5 ";
        }

        comprobar = preferences.getBoolean("crime", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 6 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 6 ";
        }

        comprobar = preferences.getBoolean("documentary", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 7 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 7 ";
        }

        comprobar = preferences.getBoolean("drama", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 8 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 8 ";
        }

        comprobar = preferences.getBoolean("family", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 9 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 9 ";
        }

        comprobar = preferences.getBoolean("fantasy", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 10 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 10 ";
        }

        comprobar = preferences.getBoolean("history", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 11 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 11 ";
        }

        comprobar = preferences.getBoolean("horror", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 12 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 12 ";
        }

        comprobar = preferences.getBoolean("music", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 13 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 13 ";
        }

        comprobar = preferences.getBoolean("musical", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 14 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 14 ";
        }

        comprobar = preferences.getBoolean("mistery", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 15 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 15 ";
        }

        comprobar = preferences.getBoolean("romance", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 16 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 16 ";
        }

        comprobar = preferences.getBoolean("sci-fi", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 17 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 17 ";
        }

        comprobar = preferences.getBoolean("sport", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 18 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 18 ";
        }

        comprobar = preferences.getBoolean("thriller", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 19 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 19 ";
        }


        comprobar = preferences.getBoolean("war", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 20 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 20 ";
        }

        comprobar = preferences.getBoolean("western", false);
        if(comprobar && primero)
        {
            primero = false;
            cadena += "id_genero1 = 21 ";
        }else if(comprobar){
            cadena += "or id_genero1 = 21 ";
        }

        return cadena;

    }

}