package com.example.silentmode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button silent , ring , status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        silent = (Button)findViewById(R.id.button);
        ring = (Button)findViewById(R.id.button2);
        status = (Button)findViewById(R.id.button3);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (!notificationManager.isNotificationPolicyAccessGranted()))
        {
            // this will launch a setting activity which will ask the permission to allow access to do not disturb
            startActivity(new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS));
        }

        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current;
                if(audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)
                    current = " Currently in Ringer Mode";
                else
                    current = " Currently is Silent Mode";
                Toast.makeText(MainActivity.this,current,Toast.LENGTH_SHORT).show();
            }
        });

    }
}