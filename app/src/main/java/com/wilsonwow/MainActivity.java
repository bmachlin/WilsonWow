package com.wilsonwow;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;
    private ImageView iv2;
    private TextView tvWow;
    private TextView tvCount;
    private MediaPlayer wow1;
    private MediaPlayer wow2;
    private int wowCount = 0;
    private SharedPreferences preferences;
    private SharedPreferences.Editor prefEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        tvWow = (TextView) findViewById(R.id.tvWow);
        tvCount = (TextView) findViewById(R.id.tvCount);
        wow1 = MediaPlayer.create(MainActivity.this, R.raw.wow2);
        wow2 = MediaPlayer.create(MainActivity.this, R.raw.wow1);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefEditor = preferences.edit();

        wowCount = preferences.getInt("wowCount", 0);
        if(wowCount == 420) {
            tvCount.setText(R.string.blazeit);
        } else {
            tvCount.setText(Integer.toString(wowCount));
        }

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wow1.start();
                updateCount();
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wow2.start();
                updateCount();
            }
        });
    }

    @Override
    public void onStop() {
        prefEditor.putInt("wowCount", wowCount);
        prefEditor.commit();
        super.onStop();
    }

    public void updateCount() {
        wowCount++;
        tvCount.setText(Integer.toString(wowCount));
        if(wowCount == 420) {
            tvCount.setText(R.string.blazeit);
        }
    }
}
