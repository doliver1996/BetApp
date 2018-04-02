package com.luis_avila.betapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    EditText correo;
    EditText nombre;
    EditText contrasena;
    EditText recontrasena;
    Button registrar;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_registro);
        correo = (EditText) findViewById(R.id.correo);
        nombre = (EditText) findViewById(R.id.nombre);
        contrasena = (EditText) findViewById(R.id.password);
        recontrasena = (EditText) findViewById(R.id.password1);
        registrar = (Button) findViewById(R.id.registro);
        mAuth = FirebaseAuth.getInstance();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();
            }
        });



    }

    private void writeNewUser(String name, String email) {
        Elemento user = new Elemento(name, email, 100);

        mDatabase.child("users").push().setValue(user);
    }

    private void validar(){
        mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            writeNewUser(nombre.getText().toString(), correo.getText().toString());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user){
        if (user != null) {
            Toast.makeText(Registro.this, "Registro de usuario exitoso.",
                    Toast.LENGTH_SHORT).show();
        }
    }




}
