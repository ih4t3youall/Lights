package ar.com.sourcesistemas.lights;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getDefaultSharedPreferences(this);
        BluetoothService.setPreferences(preferences);

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "No bluetooth detected", Toast.LENGTH_LONG).show();
        }


        String bluetoothDeviceName = BluetoothService.getDefaultBluetoothConnection();
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        List<Device> devices = new LinkedList<Device>();
        for (BluetoothDevice bluetoothDevice : bondedDevices ){
            devices.add(new Device(bluetoothDevice));
            if (bluetoothDevice.getName().equals(bluetoothDeviceName)) {
                Device device = new Device(bluetoothDevice);

                BluetoothService.setDevice(device);

                Intent intent = new Intent(MainActivity.this, LightsActivity.class);
                startActivity(intent);
                finish();
            }
        }

        ListView listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<Device> adapter = new ArrayAdapter<Device>(this, android.R.layout.simple_list_item_1,devices);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Device device = devices.get(position);

                BluetoothService.setDefaultBluetoothConnection(device.getName());
                BluetoothService.setDevice(device);

                Intent intent = new Intent(MainActivity.this, LightsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}