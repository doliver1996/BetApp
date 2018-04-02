package com.luis_avila.betapp;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    Button ingreso;
    Button registro;
    EditText correo;
    EditText contrasena;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingreso = (Button) findViewById(R.id.login_button);
        registro = (Button) findViewById(R.id.register_button);
        correo = (EditText) findViewById(R.id.user_name);
        contrasena = (EditText) findViewById(R.id.password_edit_text);
        mAuth = FirebaseAuth.getInstance();

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                String message = "hola";
                intent.putExtra("haha", message);
                startActivity(intent);
            }
        });

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loguearse();
            }
        });
    }

    private void loguearse(){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Error al iniciar sesión.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user){
        if (user != null) {
            Toast.makeText(MainActivity.this, "Inicio de sesión exitoso.",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, TabMain.class);
            String message = correo.getText().toString();
            intent.putExtra("haha", message);

            startActivity(intent);

        }
    }
}
