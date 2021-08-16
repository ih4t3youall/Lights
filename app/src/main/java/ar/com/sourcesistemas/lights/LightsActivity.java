package ar.com.sourcesistemas.lights;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import ar.com.sourcesistemas.lights.listeners.MultipleSwitch;
import ar.com.sourcesistemas.lights.listeners.SwitchListener;

public class LightsActivity extends AppCompatActivity {

    private BluetoothSocket bluetoothSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        bluetoothSocket = BluetoothService.getBluetoothSocket();

        Button velador = (Button)findViewById(R.id.velador);
        Button viga = (Button)findViewById(R.id.viga);
        Button techo = (Button)findViewById(R.id.techo);
        Button volver = (Button)findViewById(R.id.volver);

        Button offAll =  (Button) findViewById(R.id.offall);
        Button onAll =  (Button) findViewById(R.id.onall);


       velador.setOnClickListener(new SwitchListener(bluetoothSocket, new String[]{"a", "b"}));
       viga.setOnClickListener(new SwitchListener(bluetoothSocket, new String[]{"g", "h"}));
       techo.setOnClickListener(new SwitchListener(bluetoothSocket, new String[]{"c", "d"}));

       offAll.setOnClickListener(new MultipleSwitch(bluetoothSocket,new String[]{"a","c","e","g"}));
       onAll.setOnClickListener(new MultipleSwitch(bluetoothSocket,new String[]{"b","d","f","h"}));


       volver.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(LightsActivity.this, MainActivity.class);
               BluetoothService.setDefaultBluetoothConnection("null");
               startActivity(intent);
               finish();
           }
       });



    }
}