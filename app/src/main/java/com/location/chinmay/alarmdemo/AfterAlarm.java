package com.location.chinmay.alarmdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by CHINMAY on 07-09-2015.
 */
public class AfterAlarm extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener
{
    NotificationManager nm;
    TextView tvAlarm;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location myCurrentLoc;
    LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         // setContentView(R.layout.alarm_activity);

        // setup google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        tvAlarm = (TextView)findViewById(R.id.tvAlarm);
        tvAlarm.setText("hello");
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                tvAlarm.setText("changedLoc :"+location.getLatitude()+"  "+location.getLongitude());
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
        };
                 nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,AfterAlarm.class);
        //PendingIntent pi = new PendingIntent(this,0,intent,0);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
           mLocationRequest =  LocationRequest.create();
       mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
       // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
       myCurrentLoc= LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
         mLocationManager =(LocationManager)this.getSystemService(LOCATION_SERVICE);
       // mLocationManager.requestLocationUpdates();
        mLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
  tvAlarm.setText(myCurrentLoc.getLatitude()+" "+myCurrentLoc.getLongitude());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
            tvAlarm.setText("changedLoc :"+location.getLatitude()+"  "+location.getLongitude());
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
