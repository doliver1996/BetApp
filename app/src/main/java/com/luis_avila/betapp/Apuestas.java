package com.luis_avila.betapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by RetailAdmin on 19/03/2018.
 */

public class Apuestas extends Fragment {
    private  static final  String TAG = "Apuestas";
    ArrayList selecciones;
    ArrayList platas;
    ArrayList apuesta;
    ArrayList keys;
    ListView listaApuestas;
    CustomAdapterApuestas adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.partidos,container,false);

        readDatabaseEquipos();
        listaApuestas = (ListView) view.findViewById(R.id.lista_partidos);
        adapter = new CustomAdapterApuestas();

        return view;
    }

    public void readDatabaseEquipos(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("selecciones");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                selecciones = new ArrayList<String>();
                apuesta = new ArrayList<Integer>();
                platas = new ArrayList<Integer>();
                keys = new ArrayList<String>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    Elem objeto = postSnapshot.getValue(Elem.class);
                    keys.add(postSnapshot.getKey());
                    selecciones.add(objeto.equipo);
                    apuesta.add(objeto.apuesta);
                    platas.add(objeto.numero);
                }

                listaApuestas.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    class CustomAdapterApuestas extends BaseAdapter {

        @Override
        public int getCount() {
            return selecciones.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.cada_apuesta,null);
            ImageView bandera = (ImageView)view.findViewById(R.id.bandera);
            TextView numero = (TextView)view.findViewById(R.id.numapuestas);
            TextView plata = (TextView)view.findViewById(R.id.platapuestas);

            bandera.setImageResource(Partidos.images[i]);
            numero.setText("Cantidad de apuestas: "+apuesta.get(i).toString());
            plata.setText("Dinero apostado: "+platas.get(i).toString());


            return view;
        }
    }

}
