package com.pipepino.evaluacin2;

import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class Listado extends AppCompatActivity {

    private ListView lista;
    private ArrayList<String> listado=new ArrayList<String>();
    private ArrayAdapter arrayAdapter;
    private Button b_volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        b_volver=findViewById(R.id.btn_volver);

        try{

            SQLiteDatabase db=openOrCreateDatabase("BD",Context.MODE_PRIVATE,null);

            lista=findViewById(R.id.lista);

            final Cursor c= db.rawQuery("select * from atencion",null);

            int id =c.getColumnIndex("id");
            int mascota=c.getColumnIndex("Mascota");
            int duenio=c.getColumnIndex("Dueño");
            int fecha=c.getColumnIndex("Fecha");

            listado.clear();

            arrayAdapter=new ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listado);

            lista.setAdapter(arrayAdapter);

            final ArrayList<HoraAtencion> listaIngresos=new ArrayList<HoraAtencion>();

            if(c.moveToFirst()){
                do{
                    HoraAtencion atencion=new HoraAtencion();
                    atencion.id=c.getString(id);
                    atencion.nombre=c.getString(mascota);
                    atencion.duenio=c.getString(duenio);
                    atencion.fecha=c.getString(fecha);

                    listaIngresos.add(atencion);

                    listado.add(c.getString(id)+ " \t " + c.getString(mascota) + " \t " + c.getString(duenio) + " \t " + c.getString(fecha));

                }while(c.moveToNext());

                arrayAdapter.notifyDataSetChanged();

                lista.invalidateViews();

            }

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int posicion, long l) {
                    HoraAtencion atencion=listaIngresos.get(posicion);

                    Intent i=new Intent(getApplicationContext(),Edicion.class);

                    i.putExtra("id", atencion.id);
                    i.putExtra("mascota", atencion.nombre);
                    i.putExtra("duenio", atencion.duenio);
                    i.putExtra("fecha", atencion.fecha);

                    startActivity(i);

                }
            });

        }catch (Exception e) {
            Toast.makeText(this, "Error, try again e.e", Toast.LENGTH_SHORT).show();
        }

        b_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}