package com.kichukkhon.emergencyservice.Create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kichukkhon.emergencyservice.Activity.PoliceActivity;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.PoliceInfo;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.PoliceManager;
import com.kichukkhon.emergencyservice.R;

import java.util.List;

public class CreatePolice extends AppCompatActivity {
    Spinner spinnerArea;
    EditText policeStationET;
    EditText phoneNoET;
    EditText phoneNoOcET;
    PoliceInfo policeInfo;
    PoliceManager policeManager;
    AreaManager areaManager;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_police);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinnerArea=(Spinner)findViewById(R.id.spinnerArea);
        policeStationET=(EditText)findViewById(R.id.txtPoliceStation);
        phoneNoET=(EditText)findViewById(R.id.txtPhoneNo);
        phoneNoOcET=(EditText)findViewById(R.id.txtPhoneNoOc);
        policeInfo= new PoliceInfo();
        policeManager = new PoliceManager(CreatePolice.this);
        areaManager=new AreaManager(CreatePolice.this);

        List<Areas> areaList= areaManager.getAllAreas();

        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);
    }

    public void btnSavePolice(View view) {
        Intent intent = new Intent(CreatePolice.this,PoliceActivity.class);

        String policeStation = policeStationET.getText().toString();
        String phoneNo = phoneNoET.getText().toString();
        String phoneNoOc = phoneNoOcET.getText().toString();
        int areaId =  ((Areas) spinnerArea.getSelectedItem()).getAreaId();

        policeInfo.setPoliceStation(policeStation);
        policeInfo.setPhoneNo(phoneNo);
        policeInfo.setPhoneNoOc(phoneNoOc);
        policeInfo.setAreaId(areaId);

        boolean inserted = policeManager.addPoliceInfo(policeInfo);
        if (inserted) {
            startActivity(intent);
            Toast.makeText(CreatePolice.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(CreatePolice.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
    }
}
