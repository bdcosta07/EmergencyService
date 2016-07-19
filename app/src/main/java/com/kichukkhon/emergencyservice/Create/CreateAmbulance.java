package com.kichukkhon.emergencyservice.Create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kichukkhon.emergencyservice.Activity.AmbulanceActivity;
import com.kichukkhon.emergencyservice.Class.AmbulanceInfo;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Database.AmbulanceManager;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.R;

import java.util.List;

public class CreateAmbulance extends AppCompatActivity {

    Spinner spinnerArea;
    EditText txtAddress;
    EditText txtPhoneNo;
    EditText txtOrgName;
    AmbulanceInfo ambulanceInfo;
    AmbulanceManager manager;
    AreaManager areaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ambulance);

        spinnerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtOrgName = (EditText) findViewById(R.id.txtOrgName);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);

        ambulanceInfo = new AmbulanceInfo();
        manager = new AmbulanceManager(CreateAmbulance.this);
        areaManager=new AreaManager(CreateAmbulance.this);

        List<Areas> areaList= areaManager.getAllAreas();
        //List<String> areaList = new ArrayList<String>();

        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerArea.setAdapter(dataAdapter);

    }

    public void btnSaveAmbulance(View view) {
        Intent intent = new Intent(CreateAmbulance.this, AmbulanceActivity.class);

        String orgName = txtOrgName.getText().toString();
        String address = txtAddress.getText().toString();
        String phoneNo = txtPhoneNo.getText().toString();
        int areaId =  ((Areas) spinnerArea.getSelectedItem()).getAreaId();

        ambulanceInfo.setOrgName(orgName);
        ambulanceInfo.setAddress(address);
        ambulanceInfo.setPhoneNo(phoneNo);
        ambulanceInfo.setAreaId(areaId);

        boolean inserted = manager.addAmbulanceInfo(ambulanceInfo);
        if (inserted) {
            startActivity(intent);
            Toast.makeText(CreateAmbulance.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(CreateAmbulance.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
    }
}
