//package com.example.msa.hakablue;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.RemoteException;
//import android.widget.Toast;
//
//import org.altbeacon.beacon.Beacon;
//import org.altbeacon.beacon.BeaconConsumer;
//import org.altbeacon.beacon.BeaconManager;
//import org.altbeacon.beacon.Identifier;
//import org.altbeacon.beacon.RangeNotifier;
//import org.altbeacon.beacon.Region;
//import org.altbeacon.beacon.startup.BootstrapNotifier;
//import org.altbeacon.beacon.startup.RegionBootstrap;
//
//import java.util.Collection;
//
///**
// * Created by msa on 04.02.17.
// */
//
//public class IBeaconDiscorverer implements BeaconConsumer, BootstrapNotifier {
//    private BeaconManager beaconManager;
//    private RegionBootstrap regionBootstrap;
//    private Context context;
//
//    public IBeaconDiscorverer(Context context) {
//
//        Region region = new Region("haka.region",
//                Identifier.parse("c336aa38-54bb-483b-ae75-3ba707855035"), null, null);
//        regionBootstrap = new RegionBootstrap(this, region);
//
//
//
//        beaconManager = BeaconManager.getInstanceForApplication(context);
//        beaconManager.bind(this);
//
//    }
//
//    @Override
//    public void onBeaconServiceConnect() {
//        beaconManager.addRangeNotifier(new RangeNotifier() {
//            @Override
//            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
//                if (beacons.size() > 0) {
//                    regionBootstrap.addRegion(region);
//                    Toast.makeText(context, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.", Toast.LENGTH_SHORT).show();
////                    Log.i(TAG, "The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters away.");
//                }
//            }
//        });
//
//        try {
//            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
//        } catch (RemoteException e) {
//        }
//    }
//
//    @Override
//    public Context getApplicationContext() {
//        return null;
//    }
//
//    @Override
//    public void unbindService(ServiceConnection serviceConnection) {
//
//    }
//
//    @Override
//    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
//        return false;
//    }
//
//    @Override
//    public void didEnterRegion(Region region) {
//
//    }
//
//    @Override
//    public void didExitRegion(Region region) {
//
//    }
//
//    @Override
//    public void didDetermineStateForRegion(int i, Region region) {
//
//    }
//}
