package com.pipepino.evaluacin2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Ingreso extends AppCompatActivity {

    private static final String BROKER= "tcp://broker.emqx.io:1883";
    private static final String CLIENT_ID= "cliente_felipeeva3";
    private static final String TOPIC_SUB= "pipeeval3";
    private MqttHandler mqttHandler;

    private EditText ed_mascota, ed_duenio, ed_fecha;
    private Button b_ingresar, b_volver;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference horasRef = FirebaseDatabase.getInstance().getReference("horas");


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

        mqttHandler=new MqttHandler();

        mqttHandler.connect(BROKER,CLIENT_ID);

        mqttHandler.subscribe(TOPIC_SUB);

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

        Map<String,Object> horaAtencionData=new HashMap<>();

        String mascota=ed_mascota.getText().toString();
        String duenio=ed_duenio.getText().toString();
        String fecha=ed_fecha.getText().toString();
        //String id=db.
        horaAtencionData.put("nombre",mascota);
        horaAtencionData.put("duenio",duenio);
        horaAtencionData.put("fecha",fecha);

        horasRef.push().setValue(horaAtencionData).addOnSuccessListener(aVoid -> Log.d("Firebase","Operación exitosa"))
                .addOnFailureListener(e -> Log.e("Firebase","Error en la operacion",e));

        ed_mascota.setText("");
        ed_duenio.setText("");
        ed_fecha.setText("");

        mqttHandler.publish(TOPIC_SUB,"Dato agregado a la database de firebase: Mascota: "
                +mascota+" Dueño: "+ duenio+ " Fecha: "+fecha);

        Toast.makeText(this,"Datos agregados satisfactoriamente en la base de datos Firebase.",
                Toast.LENGTH_LONG).show();


    }
}