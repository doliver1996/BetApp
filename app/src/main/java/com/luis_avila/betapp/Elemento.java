package com.luis_avila.betapp;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by RetailAdmin on 28/11/2016.
 */


@IgnoreExtraProperties
public class Elemento {

    public String Nombre;
    public String correo;
    public int saldo;



    public Elemento() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Elemento(String username, String email, int saldo) {
        this.Nombre = username;
        this.correo = email;
        this.saldo = saldo;
    }

}