package ar.com.sourcesistemas.lights.listeners;

import android.bluetooth.BluetoothSocket;
import android.view.View;

import java.io.IOException;

public class SwitchListener implements View.OnClickListener {

    private boolean lastState;
    private BluetoothSocket bluetoothSocket;
    private String[] commands;

    public SwitchListener(BluetoothSocket bluetoothSocket, String[] commands){
        this.bluetoothSocket = bluetoothSocket;
        this.commands = commands;
    }

    @Override
    public void onClick(View view) {
        lastState = !lastState;
        if (!bluetoothSocket.isConnected()){
            try {
                bluetoothSocket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] toSend;
        if (lastState)
            toSend = commands[0].getBytes();
        else
            toSend = commands[1].getBytes();

        try {
            bluetoothSocket.getOutputStream().write(toSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
