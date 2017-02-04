package com.example.msa.hakablue;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import java.util.Collection;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements BeaconConsumer, BootstrapNotifier,RangeNotifier {


    private ListView listView;
    private ArrayAdapter<String> mBTArrayAdapter;



    private static final String TAG = "BeaconReferenceApp";
    private RegionBootstrap regionBootstrap;
    private BackgroundPowerSaver backgroundPowerSaver;
    private MainActivity rangingActivity = null;
    BeaconManager beaconManager;

//    private static final String TAG = "a";
//    private BeaconManager beaconManager;
//    private RegionBootstrap regionBootstrap;

    Button button;

    BluetoothLeScanner bluetoothLeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.ref);
        mBTArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(mBTArrayAdapter);


        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("s:0-1=feaa,m:2-2=00,p:3-3:-41,i:4-13,i:14-19"));

//        Region region = new Region("backgroundRegion", Identifier.parse("c336aa38-54bb-483b-ae75-3ba707855035"), null, null);
        Region region = new Region("backgroundRegion", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);


        beaconManager.setBackgroundBetweenScanPeriod(30000l);
        beaconManager.setForegroundBetweenScanPeriod(2000l);
        beaconManager.bind(this);


////        Region region = new Region("haka.region", Identifier.parse("c336aa38-54bb-483b-ae75-3ba707855035"), null, null);
//        Region region = new Region("haka.region", null, null, null);
//        regionBootstrap = new RegionBootstrap(this, region);
//
//        beaconManager = BeaconManager.getInstanceForApplication(this);
//        beaconManager.bind(this);
////        beaconManager.addRangeNotifier(this);
//
//        registerReceiver(blReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                MainActivity.this.finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {

        Toast.makeText(this, "conected", Toast.LENGTH_SHORT).show();

        beaconManager.setRangeNotifier(this);

//        Toast.makeText(this, "something", Toast.LENGTH_SHORT).show();

//        beaconManager.addRangeNotifier(new RangeNotifier() {
//            @Override
//            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
//                Toast.makeText(MainActivity.this, "someth", Toast.LENGTH_SHORT).show();
//                if (beacons.size() > 0) {
//                    regionBootstrap.addRegion(region);
//                    mBTArrayAdapter.add(beacons.iterator().next().getBluetoothName());
//
//                    Toast.makeText(MainActivity.this, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.", Toast.LENGTH_SHORT).show();
////                    Log.i(TAG, "The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters away.");
//                }
//            }
//        });
//
//        try {
//            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
//        } catch (RemoteException e) {
//        }
    }

    ///////////////////////////////////////////////////////
    @Override
    public void didEnterRegion(Region region) {
        Toast.makeText(rangingActivity, "enter gerion", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "did enter region.");
        try {
            beaconManager.startRangingBeaconsInRegion(region);
        }
        catch (RemoteException e) {
            if (BuildConfig.DEBUG) Log.d(TAG, "Can't start ranging");
        }
//        Toast.makeText(this, "entered", Toast.LENGTH_SHORT).show();
//        mBTArrayAdapter.add("entered region");
//        mBTArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void didExitRegion(Region region) {

        Toast.makeText(rangingActivity, "exit regiion", Toast.LENGTH_SHORT).show();
        try {
            beaconManager.stopRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

//        Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
//        mBTArrayAdapter.add("exit region");
//        mBTArrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {
        Log.d(TAG,"I have just switched from seeing/not seeing beacons: " + i);
    }

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


        }
    };

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        Toast.makeText(this, "somtehshdasjkdhaskjdhaskjdhkjash", Toast.LENGTH_SHORT).show();



        if (collection.size() > 0) {
            for (Beacon b : collection) {
                if(b.getId2().toString().equals("0x6d767674636e")) {
                    Log.e(TAG, "Beacon with my Instance ID found!");
                    sendNotification("Beacon with my Instance ID found!");
                }
            }
        }

    }


    private void sendNotification(String text) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Beacon Reference Application")
                        .setContentText(text)
//                        .setSmallIcon(R.drawable.)
                ;

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(new Intent(this, MainActivity.class));
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

}
