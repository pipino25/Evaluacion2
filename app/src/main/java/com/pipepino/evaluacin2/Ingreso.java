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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ingreso extends AppCompatActivity {

    private EditText ed_mascota, ed_duenio, ed_fecha;
    private Button b_ingresar, b_volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingreso);
        ed_mascota=findViewById(R.id.et_nombre);
        ed_duenio=findViewById(R.id.et_duenio);
        ed_fecha=findViewById(R.id.et_fecha);

        b_ingresar=findViewById(R.id.btn_ingresar);
        b_volver=findViewById(R.id.btn_volver);

        b_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        b_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();
            }
        });
    }
    public void insertar(){
        try{
            String mascota=ed_mascota.getText().toString();
            String duenio=ed_duenio.getText().toString();
            String fecha=ed_fecha.getText().toString();

            SQLiteDatabase db=openOrCreateDatabase("BD",Context.MODE_PRIVATE,null);

            db.execSQL("CREATE TABLE IF NOT EXISTS atencion(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Mascota VARCHAR, Dueño VARCHAR, Fecha VARCHAR)");

            String sql = "insert into atencion(Mascota, Dueño, Fecha) values(?, ?, ?)";
            SQLiteStatement statement=db.compileStatement(sql);

            statement.bindString(1, mascota);
            statement.bindString(2, duenio);
            statement.bindString(3, fecha);

            statement.execute();

            Toast.makeText(this,"Datos agregados satisfactoriamente en la base de datos.",
                    Toast.LENGTH_LONG).show();

            ed_mascota.setText("");
            ed_duenio.setText("");
            ed_fecha.setText("");

        }catch(Exception ex){
            Toast.makeText(this,"Error de ingreso",Toast.LENGTH_LONG).show();
        }
    }
}