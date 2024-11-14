package com.pipepino.evaluacin2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Edicion extends AppCompatActivity {

    private EditText ed_mascota, ed_duenio, ed_fecha, ed_id;
    private Button b_editar,b_eliminar,b_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edicion);

        ed_mascota=findViewById(R.id.editar_nombre);
        ed_duenio=findViewById(R.id.editar_duenio);
        ed_fecha=findViewById(R.id.editar_fecha);
        ed_id=findViewById(R.id.id);

        b_editar = findViewById(R.id.btn_editar);
        b_eliminar = findViewById(R.id.btn_eliminar);
        b_volver = findViewById(R.id.btn_volver);

        Intent i=getIntent();

        String et_id = i.getStringExtra("id").toString();
        String et_mascota = i.getStringExtra("mascota").toString();
        String et_duenio = i.getStringExtra("duenio").toString();
        String et_fecha = i.getStringExtra("fecha").toString();

        ed_id.setText(et_id);
        ed_mascota.setText(et_mascota);
        ed_duenio.setText(et_duenio);
        ed_fecha.setText(et_fecha);

        b_editar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View view){editar();}
        });

        b_eliminar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View view){eliminar();}
        });
        b_volver.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View view){
                Intent i=new Intent(getApplicationContext(),Listado.class);
                startActivity(i);
            }
        });

    }

    public void editar(){
        try{
            String id =ed_id.getText().toString();
            String mascota=ed_mascota.getText().toString();
            String duenio=ed_duenio.getText().toString();
            String fecha=ed_fecha.getText().toString();

            SQLiteDatabase db=openOrCreateDatabase("BD",Context.MODE_PRIVATE,null);

            String sql = "update atencion set Mascota=?, Dueño=?, Fecha=? where id=?";
            SQLiteStatement statement=db.compileStatement(sql);

            statement.bindString(1, mascota);
            statement.bindString(2, duenio);
            statement.bindString(3, fecha);
            statement.bindString(4, id);
            statement.execute();

            Toast.makeText(this, "Datos editados", Toast.LENGTH_LONG).show();

            ed_mascota.setText("");
            ed_duenio.setText("");
            ed_fecha.setText("");
            ed_mascota.requestFocus();

        }catch (Exception ex) {
            Toast.makeText(this, "Error, no se pudieron editar los datos.",
                    Toast.LENGTH_LONG).show();

        }
    }

    public void eliminar(){

        try{

            String id =ed_id.getText().toString();

            SQLiteDatabase db=openOrCreateDatabase("BD",Context.MODE_PRIVATE,null);

            String sql = "delete from atencion where id=?";
            SQLiteStatement statement=db.compileStatement(sql);

            statement.bindString(1, id);
            statement.execute();

            Toast.makeText(this, "Datos eliminados", Toast.LENGTH_LONG).show();

            ed_mascota.setText("");
            ed_duenio.setText("");
            ed_fecha.setText("");
            ed_mascota.requestFocus();

        }catch (Exception ex) {
            Toast.makeText(this, "Error, no se pudieron borrar los datos.",
                    Toast.LENGTH_LONG).show();

        }
    }
}