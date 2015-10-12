
//this is working : the best
package com.location.chinmay.alarmdemo;

/**
 * Created by CHINMAY on 12-10-2015.
 */
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MyService extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener
{
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    //NotificationManager nm;
    String toast="not connected yet";
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location myCurrentLoc;
    LocationManager mLocationManager;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.

        //version 1.0----------------
        // setup google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
        //waits for onConnected CallBack
        //----------------------------

       // Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
        // Toast.makeText(this, "after connectn: "+toast, Toast.LENGTH_LONG).show();

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
        Toast.makeText(this, "Service Destroyed: "+toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(Bundle bundle) {
      //  myCurrentLoc= LocationServices.FusedLocationApi.getLastLocation(
       //         mGoogleApiClient);
       Toast.makeText(this, "connected", Toast.LENGTH_LONG).show();

         //------------------------------
        mLocationRequest =  LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
        myCurrentLoc= LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        mLocationManager =(LocationManager)this.getSystemService(LOCATION_SERVICE);
        // mLocationManager.requestLocationUpdates();
        mLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        toast = myCurrentLoc.getLatitude() + " " + myCurrentLoc.getLongitude();
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();

    stopSelf();//calls destroy method
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



}
