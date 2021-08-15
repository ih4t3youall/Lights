package ar.com.sourcesistemas.lights.listeners;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.view.View;

import java.io.IOException;

public class SwitchListener implements View.OnClickListener {

    private boolean lastState;
    private BluetoothDevice bluetoothDevice;
    private String[] commands;

    public SwitchListener(BluetoothDevice bluetoothDevice, String[] commands){
        this.bluetoothDevice = bluetoothDevice;
        this.commands = commands;
    }

    @Override
    public void onClick(View view) {
        lastState = !lastState;

        bluetoothDevice.get

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
