package ar.com.sourcesistemas.lights.listeners;

import android.bluetooth.BluetoothSocket;
import android.view.View;

import java.io.IOException;

public class MultipleSwitch   implements View.OnClickListener {

    private String [] commands;
    private BluetoothSocket bluetoothSocket;

    public MultipleSwitch(BluetoothSocket bluetoothSocket, String [] commands){
        this.commands = commands;
        this.bluetoothSocket =bluetoothSocket;
    }

    @Override
    public void onClick(View view) {

        if (!bluetoothSocket.isConnected()) {
            try {
                bluetoothSocket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            for (String command : commands){
                bluetoothSocket.getOutputStream().write(command.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
