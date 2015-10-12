package com.location.chinmay.alarmdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/*
basically wr are using this approach to make app workable even if device is in sleep mode
use wake up verion of alarm
 */

public class MainActivity extends Activity {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BroadcastReceiver br=  new OnAlarm();
       /* = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Alarm time has been reached", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        };*/
        this.registerReceiver(br, new IntentFilter("com.location.chinmay.alarmdemo.AFTERALARM"));
       //Intent intent = new Intent(this,OnAlarm.class);
     //   pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),234324243, new Intent("com.location.chinmay.alarmdemo.AFTERALARM"),0);

       // alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

      //  alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
        //        SystemClock.elapsedRealtime()+10*1000,pendingIntent);
        Toast.makeText(this, "Alarm set in 5 seconds",
                Toast.LENGTH_LONG).show();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
