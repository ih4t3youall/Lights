package ar.com.sourcesistemas.lights;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

public class Device {

    private BluetoothDevice btDevice;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothSocket bluetoothSocket = null;

    public Device(BluetoothDevice btDevice){
        this.btDevice = btDevice;
    }

    public BluetoothDevice  getDevice(){
        return btDevice;
    }

    public String getName(){
        return btDevice.getName();
    }

    public BluetoothSocket  connect() {

        if (bluetoothSocket != null){
            if (bluetoothSocket.isConnected()){
                return bluetoothSocket;
            }
        }
        try {
            bluetoothSocket = btDevice.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
            return bluetoothSocket;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String toString(){
        return btDevice.getName();
    }
}
