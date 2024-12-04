package com.pipepino.evaluacin2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edicion extends AppCompatActivity {

    private EditText ed_mascota, ed_duenio, ed_fecha, ed_id;
    private Button b_editar,b_eliminar,b_volver;

    private MqttHandler mqttHandler;

    private static final String BROKER= "tcp://broker.emqx.io:1883";
    private static final String CLIENT_ID= "cliente_felipeeva3";
    private static final String TOPIC_SUB= "pipeeval3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edicion);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference horasRef = database.getReference("horas");

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

        mqttHandler=new MqttHandler();

        mqttHandler.connect(BROKER,CLIENT_ID);

        mqttHandler.subscribe(TOPIC_SUB);

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

        String id =ed_id.getText().toString();
        String mascota=ed_mascota.getText().toString();
        String duenio=ed_duenio.getText().toString();
        String fecha=ed_fecha.getText().toString();



        HoraAtencion nuevahora= new HoraAtencion(id,mascota,duenio,fecha);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference horasRef = database.getReference("horas");
        DatabaseReference horasAct=horasRef.child(id);
        horasAct.setValue(nuevahora);

        Toast.makeText(this, "Datos editados", Toast.LENGTH_LONG).show();

        mqttHandler.publish(TOPIC_SUB,"Dato en la database de firebase editado..."
                +"/t"+" id: "+id+" Nuevos datos: "+"/t"+ "Mascota: "
                +mascota+" Dueño: "+ duenio+ " Fecha: "+fecha);

    }

    public void eliminar(){

        String id =ed_id.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference horasRef = database.getReference("horas");
        DatabaseReference horasDel=horasRef.child(id);
        horasDel.removeValue();

        ed_mascota.setText("");
        ed_duenio.setText("");
        ed_fecha.setText("");
        ed_mascota.requestFocus();
        Toast.makeText(this, "Datos eliminados", Toast.LENGTH_LONG).show();

        mqttHandler.publish(TOPIC_SUB,"Dato en la database de firebase de id: "+id+"/t"+
                " ha sido eliminado");

    }
}