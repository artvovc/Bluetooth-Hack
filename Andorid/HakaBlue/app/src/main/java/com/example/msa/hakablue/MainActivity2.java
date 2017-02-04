package com.example.msa.hakablue;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Button mDiscoverBtn;
    private Button clear;
    private BluetoothAdapter mBTAdapter;
    private ArrayAdapter<String> mBTArrayAdapter;
    private ArrayAdapter<String> mBTArrayAdapter2;
    private ListView mDevicesListView;
    private ListView mDevicesListView2;
    private CheckBox mLED1;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma2);

        mDiscoverBtn = (Button) findViewById(R.id.discover);
        clear = (Button) findViewById(R.id.clear);



        mBTArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mBTArrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio

        textView = (TextView) findViewById(R.id.power);

        mDevicesListView2 = (ListView) findViewById(R.id.devicesListView2);
        mDevicesListView2.setAdapter(mBTArrayAdapter2); // assign model to view

        mDevicesListView = (ListView) findViewById(R.id.devicesListView);
        mDevicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        mDevicesListView.setOnItemClickListener(mDeviceClickListener);


        mDiscoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discover(v);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBTArrayAdapter.clear();
                mBTArrayAdapter2.clear();
                mBTArrayAdapter.notifyDataSetChanged();
                mBTArrayAdapter.notifyDataSetChanged();
            }
        });
    }


    private void discover(View view) {
        // Check if the device is already discovering
        if (mBTAdapter.isDiscovering()) {
            mBTAdapter.cancelDiscovery();
            Toast.makeText(getApplicationContext(), "Discovery stopped", Toast.LENGTH_SHORT).show();
        } else {
            if (mBTAdapter.isEnabled()) {
                mBTArrayAdapter.clear(); // clear items
                mBTAdapter.startDiscovery();
                Toast.makeText(getApplicationContext(), "Discovery started", Toast.LENGTH_SHORT).show();
                registerReceiver(blReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    static int max = 0;

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int limit = 3;

            List<String> mac = new ArrayList<>();

            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MAX_VALUE);
                int uuid = intent.getShortExtra(BluetoothDevice.EXTRA_UUID,Short.MAX_VALUE);




                if (rssi < max) {
                    max = rssi;
                    textView.setText(device.getName() + "\n" + device.getAddress() + "powe : " + rssi + " maj" + device.getBluetoothClass().getMajorDeviceClass());
                }


                mBTArrayAdapter2.add(device.getName() + "\n" + device.getAddress() + "powe : " + rssi + "\n uid " + uuid + " maj" + device.getBluetoothClass().getMajorDeviceClass());
                mBTArrayAdapter.clear();
                if (limit < 0) {
                    if (mac.contains(device.getAddress())) {
                        mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress() + "powe : " + rssi);
                    }
                } else {
                    mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress() + "powe : " + rssi);
                    mac.add(device.getAddress());
                    limit--;
                }
                mBTArrayAdapter.notifyDataSetChanged();
                mBTArrayAdapter2.notifyDataSetChanged();
//                }
            }
        }
    };


//    public String getMajor() {
//        major = String.valueOf( (mScanRecord[25] & 0xff) * 0x100 + (mScanRecord[26] & 0xff));
//        return major;
//    }
//
//    public String getMinor() {
//        minor =     String.valueOf( (mScanRecord[27] & 0xff) * 0x100 + (mScanRecord[28] & 0xff));
//        return minor;
//    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            String info = ((TextView) v).getText().toString();
            Toast.makeText(getBaseContext(), "Bluetooth " + info, Toast.LENGTH_SHORT).show();
        }
    };

}
