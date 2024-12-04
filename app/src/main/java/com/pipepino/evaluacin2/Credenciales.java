package com.pipepino.evaluacin2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Credenciales extends AppCompatActivity {

    private EditText username,password;
    private Button ingresar;
    private static final String BROKER= "tcp://broker.emqx.io:1883";
    private static final String CLIENT_ID= "cliente_felipeeva3";
    private static final String TOPIC_SUB= "pipeeval3";
    private MqttHandler mqttHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_credenciales);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        ingresar=findViewById(R.id.btnIngresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });

    }
    public void ingresar(){

        String usuario=username.getText().toString();
        String contrasenia=password.getText().toString();

        if ((usuario.equals("sistemas")&&contrasenia.equals("1234"))){

            mqttHandler=new MqttHandler();

            mqttHandler.connect(BROKER,CLIENT_ID);

            mqttHandler.subscribe(TOPIC_SUB);

            mqttHandler.publish(TOPIC_SUB,"Ingreso al sistema de base de datos desde la aplicación en android");

            Intent i=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }else{

            Toast.makeText(this,"Usuario o Contraseña Incorrectos",
                    Toast.LENGTH_LONG).show();
        }
    }
}