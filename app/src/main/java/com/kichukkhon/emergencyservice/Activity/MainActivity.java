package com.kichukkhon.emergencyservice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kichukkhon.emergencyservice.ESBaseActivity;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.R;

public class MainActivity extends ESBaseActivity {
    ImageView imgAmbulance, imgFireService, imgPolice;
    SharedPreference preference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
