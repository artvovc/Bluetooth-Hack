package com.example.msa.hakablue;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;


public class BeaconActivity extends AppCompatActivity implements BeaconConsumer {

    public static final String TAG = "BeaconsEverywhere";
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_init);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        addNotifier();
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));

        Button button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                        Sender sender = new Sender(1505, "233.0.0.1", 8888, "10.21.0.36");
                        sender.request("variabila");



//                            String messageStr="Hello Android!";
//                            int server_port = 8888;
//                            DatagramSocket s = null;
//                            s = new DatagramSocket();
//                            InetAddress local = InetAddress.getByName("10.244.52.187");
//                            int msg_length=messageStr.length();
//                            byte[] message = messageStr.getBytes();
//                            DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
//                            s.send(p);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).start();


//                startActivity(new Intent(BeaconActivity.this, RangingActivity.class));
//                BeaconActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.bind(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        final Region region = new Region("myBeaons", Identifier.parse("C336AA38-54BB-483B-AE75-3BA707855035"), null, null);


        try {
            beaconManager.startMonitoringBeaconsInRegion(region);
            addNotifier();
            Toast.makeText(this, "no error", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                try {
                    Log.d(TAG, "didEnterRegion");
                    beaconManager.startRangingBeaconsInRegion(region);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void didExitRegion(Region region) {
                try {
                    Log.d(TAG, "didExitRegion");
                    beaconManager.stopRangingBeaconsInRegion(region);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {

            }
        });

        addNotifier();


    }

    private void addNotifier() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                for (Beacon oneBeacon : beacons) {

                    toast();

//                    Toast.makeText(this,
//                            "distance: " + oneBeacon.getDistance() + " id:" + oneBeacon.getId1() + "/" + oneBeacon.getId2() + "/" + oneBeacon.getId3()
//                            , Toast.LENGTH_SHORT).show();

                    Log.d(TAG, "distance: " + oneBeacon.getDistance() + " id:" + oneBeacon.getId1() + "/" + oneBeacon.getId2() + "/" + oneBeacon.getId3());
                    toast();
                    Log.d(TAG,"===============================================================");
                }
            }

        });
    }

    private void toast() {
        Toast.makeText(this, "beacon", Toast.LENGTH_SHORT).show();
    }

}