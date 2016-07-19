package com.kichukkhon.emergencyservice.EditActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class EditAmbulance extends AppCompatActivity {
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
        setContentView(R.layout.activity_ambulance_edit);

        spinnerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtOrgName = (EditText) findViewById(R.id.txtOrgName);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);


        //ambulanceInfo = new AmbulanceInfo();
        manager = new AmbulanceManager(EditAmbulance.this);
        areaManager=new AreaManager(EditAmbulance.this);

        fillViews();

    }

    public void fillViews(){
        int id=getIntent().getIntExtra("id",0);

        List<Areas> areaList= areaManager.getAllAreas();
        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerArea.setAdapter(dataAdapter);

        ambulanceInfo=manager.getAmbulanceInfo(id);

        int pos=0;
        Areas area=areaManager.getAreaById(ambulanceInfo.getAreaId());
        for (int i=0; i<spinnerArea.getCount();i++)
        {
            if(spinnerArea.getItemAtPosition(i).equals(area)){
                pos=i;
                break;
            }
        }
        spinnerArea.setSelection(pos);
        txtOrgName.setText(ambulanceInfo.getOrgName());
        txtAddress.setText(ambulanceInfo.getAddress());
        txtPhoneNo.setText(ambulanceInfo.getPhoneNo());
    }

    public void btnSaveAmbulance(View view) {
        Intent intent = new Intent(EditAmbulance.this, AmbulanceActivity.class);

        String orgName = txtOrgName.getText().toString();
        String address = txtAddress.getText().toString();
        String phoneNo = txtPhoneNo.getText().toString();
        int areaId =  ((Areas) spinnerArea.getSelectedItem()).getAreaId();

        ambulanceInfo.setOrgName(orgName);
        ambulanceInfo.setAddress(address);
        ambulanceInfo.setPhoneNo(phoneNo);
        ambulanceInfo.setAreaId(areaId);

        AmbulanceManager manager=new AmbulanceManager(EditAmbulance.this);
        int id=ambulanceInfo.getId();

        boolean updated = manager.updateAmbulanceInfo(id,ambulanceInfo);
        if (updated) {
            startActivity(intent);
            Toast.makeText(EditAmbulance.this, "Data Updated", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(EditAmbulance.this, "Data not Updated", Toast.LENGTH_SHORT).show();
    }

    public void btnDeleteAmbulance(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditAmbulance.this);
        builder1.setMessage("Do you really want to delete this?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(EditAmbulance.this, AmbulanceActivity.class);
                        int ambulanceId=ambulanceInfo.getId();
                        boolean deleted= manager.deleteAmbulanceInfo(ambulanceId);

                        if (deleted) {
                            startActivity(intent);
                            Toast.makeText(EditAmbulance.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(EditAmbulance.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}
