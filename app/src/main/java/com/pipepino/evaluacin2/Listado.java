package com.pipepino.evaluacin2;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class Listado extends AppCompatActivity {

    private ListView lista;
    private ArrayList<String> listado=new ArrayList<String>();
    private ArrayAdapter arrayAdapter;
    private Button b_volver;

    /*FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref= db.getReference("horas");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        b_volver=findViewById(R.id.btn_volver);

        try {
            // Inicializa Firebase
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference horasRef = database.getReference("horas");

            lista = findViewById(R.id.lista);
            listado.clear();
            arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listado);
            lista.setAdapter(arrayAdapter);

            final ArrayList<HoraAtencion> listaIngresos = new ArrayList<>();

            // Lee datos desde Firebase
            horasRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listado.clear(); // Limpia la lista antes de llenarla
                    listaIngresos.clear();
                    int contador=0;
                    for (DataSnapshot horaSnapshot : snapshot.getChildren()) {
                        HoraAtencion atencion = horaSnapshot.getValue(HoraAtencion.class);
                        atencion.setId(horaSnapshot.getKey());
                        if (atencion != null) {
                            listaIngresos.add(atencion);
                            listado.add(contador + " \t " + atencion.nombre + " \t " + atencion.duenio + " \t " + atencion.fecha);
                        }
                        contador++;
                    }

                    arrayAdapter.notifyDataSetChanged();
                    lista.invalidateViews();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Listado.this, "Error al leer datos de Firebase", Toast.LENGTH_SHORT).show();
                }
            });

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                    HoraAtencion atencion = listaIngresos.get(posicion);

                    Intent i = new Intent(getApplicationContext(), Edicion.class);

                    i.putExtra("id", atencion.id);
                    i.putExtra("mascota", atencion.nombre);
                    i.putExtra("duenio", atencion.duenio);
                    i.putExtra("fecha", atencion.fecha);

                    startActivity(i);
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error, intenta de nuevo", Toast.LENGTH_SHORT).show();
        }

        b_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


    }
}