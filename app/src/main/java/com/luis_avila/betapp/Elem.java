package com.luis_avila.betapp;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by RetailAdmin on 28/11/2016.
 */


@IgnoreExtraProperties
public class Elem {

    public  int apuesta;
    public String equipo;
    public  int numero;




    public Elem() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Elem(int apuesta, String equipo, int numero) {

        this.apuesta = apuesta;
        this.equipo = equipo;
        this.numero = numero;
    }

}