package com.kichukkhon.emergencyservice.Create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kichukkhon.emergencyservice.Activity.FireServiceActivity;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.FireServiceInfo;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.FireServiceManager;
import com.kichukkhon.emergencyservice.R;

import java.util.List;

public class CreateFireService extends AppCompatActivity {Spinner spinnerArea;
    EditText txtAddress;
    EditText txtPhoneNo;
    FireServiceInfo fireServiceInfo;
    FireServiceManager fireServiceManager;
    AreaManager areaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fire_service);

        spinnerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);


        fireServiceInfo = new FireServiceInfo();
        fireServiceManager = new FireServiceManager(CreateFireService.this);
        areaManager=new AreaManager(CreateFireService.this);

        List<Areas> areaList= areaManager.getAllAreas();

        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerArea.setAdapter(dataAdapter);
    }

    public void btnSaveFireService(View view) {
        Intent intent = new Intent(CreateFireService.this,FireServiceActivity.class);

       String address = txtAddress.getText().toString();
        String phoneNo = txtPhoneNo.getText().toString();
        int areaId =  ((Areas) spinnerArea.getSelectedItem()).getAreaId();

        fireServiceInfo.setAddress(address);
        fireServiceInfo.setPhoneNo(phoneNo);
        fireServiceInfo.setAreaId(areaId);

        boolean inserted = fireServiceManager.addFireServiceInfo(fireServiceInfo);
        if (inserted) {
            startActivity(intent);
            Toast.makeText(CreateFireService.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(CreateFireService.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
    }
}
