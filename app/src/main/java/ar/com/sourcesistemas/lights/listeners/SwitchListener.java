package ar.com.sourcesistemas.lights.listeners;

import android.bluetooth.BluetoothSocket;
import android.view.View;

import java.io.IOException;

import ar.com.sourcesistemas.lights.BluetoothService;

public class SwitchListener implements View.OnClickListener {

    private boolean lastState;
    private String[] commands;

    public SwitchListener(String[] commands){
        this.commands = commands;
    }

    @Override
    public void onClick(View view) {
        lastState = !lastState;
        BluetoothSocket  bluetoothSocket =  BluetoothService.getBluetoothSocket();

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
