package com.kichukkhon.emergencyservice.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kichukkhon.emergencyservice.ESBaseActivity;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.R;

public class MainActivity extends ESBaseActivity {
    ImageView imgAmbulance, imgFireService, imgPolice;
    SharedPreference preference;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgAmbulance=(ImageView)findViewById(R.id.imgAmbulance);
        imgFireService=(ImageView)findViewById(R.id.imgFireService);
        imgPolice=(ImageView)findViewById(R.id.imgPolice);


        imgAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AmbulanceActivity.class);
                startActivity(intent);

            }
        });

        imgPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PoliceActivity.class);
                startActivity(intent);
            }
        });

        imgFireService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,FireServiceActivity.class);
                startActivity(intent);
            }
        });

        SharedPreference preference = new SharedPreference(this);

        if (!preference.AlreadyVisited())
        {
            preference.setAlreadyVisitStatus();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("First thing first. Please select your area from settings");
            // Add the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                }
            });

            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
