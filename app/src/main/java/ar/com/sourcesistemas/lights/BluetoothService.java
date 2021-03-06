package ar.com.sourcesistemas.lights;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BluetoothService {

    private static BluetoothSocket bluetoothSocket;
    private static String FILENAME = "configuration.txt";
    private static SharedPreferences  sharedPreferences;
    private static String defaultBluetooth = "";

    public static void setBluetoothSocket(BluetoothSocket socket ){
       bluetoothSocket = socket;
    }

    public static BluetoothSocket  getBluetoothSocket(){
        return bluetoothSocket;
    }


    public static void setPreferences(SharedPreferences preferences) {
        sharedPreferences = preferences;
    }

    public static void setDefaultBluetoothConnection(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bluetoothDefault",name);
        editor.apply();
    }

    public static String getDefaultBluetoothConnection(){
        String bluetoothName = sharedPreferences.getString("bluetoothDefault","");
        return bluetoothName;
    }


}
