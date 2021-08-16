package ar.com.sourcesistemas.lights.listeners;

import android.bluetooth.BluetoothSocket;
import android.view.View;

import java.io.IOException;

import ar.com.sourcesistemas.lights.BluetoothService;

public class MultipleSwitch   implements View.OnClickListener {

    private String [] commands;

    public MultipleSwitch(String [] commands){
        this.commands = commands;
    }

    @Override
    public void onClick(View view) {

        BluetoothSocket  bluetoothSocket =  BluetoothService.getBluetoothSocket();

        try {
            for (String command : commands){
                bluetoothSocket.getOutputStream().write(command.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
