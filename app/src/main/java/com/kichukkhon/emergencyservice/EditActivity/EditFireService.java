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

import com.kichukkhon.emergencyservice.Activity.FireServiceActivity;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.FireServiceInfo;
import com.kichukkhon.emergencyservice.Class.Utils;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.FireServiceManager;
import com.kichukkhon.emergencyservice.R;

import java.util.List;

public class EditFireService extends AppCompatActivity {

    Spinner spinnerArea;
    EditText txtAddress;
    EditText txtPhoneNo;
    FireServiceInfo fireServiceInfo;
    FireServiceManager fireServiceManager;
    AreaManager areaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fire_service);

        spinnerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);


        //FireStationInfo = new FireStationInfo();
        fireServiceManager = new FireServiceManager(EditFireService.this);
        areaManager = new AreaManager(EditFireService.this);

        fillViews();
    }

    public void fillViews() {
        int id = getIntent().getIntExtra("id", 0);

        List<Areas> areaList = areaManager.getAllAreas();
        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);

        fireServiceInfo = fireServiceManager.getFireServiceInfo(id);

        int selectedAreaId = fireServiceInfo.getAreaId();
        spinnerArea.setSelection(Utils.getIndex(spinnerArea, selectedAreaId));
        txtAddress.setText(fireServiceInfo.getAddress());
        txtPhoneNo.setText(fireServiceInfo.getPhoneNo());
    }

    public void btnSaveFireService(View view) {
        Intent intent = new Intent(EditFireService.this, FireServiceActivity.class);

        String address = txtAddress.getText().toString();
        String phoneNo = txtPhoneNo.getText().toString();
        int areaId = ((Areas) spinnerArea.getSelectedItem()).getAreaId();

        fireServiceInfo.setAddress(address);
        fireServiceInfo.setPhoneNo(phoneNo);
        fireServiceInfo.setAreaId(areaId);

        fireServiceManager = new FireServiceManager(EditFireService.this);
        int id = fireServiceInfo.getId();

        boolean updated = fireServiceManager.updateFireServiceInfo(id, fireServiceInfo);
        if (updated) {
            startActivity(intent);
            Toast.makeText(EditFireService.this, "Data Updated", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(EditFireService.this, "Data not Updated", Toast.LENGTH_SHORT).show();
    }

    public void btnDeleteFireService(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditFireService.this);
        builder1.setMessage("Do you really want to delete this?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(EditFireService.this, FireServiceActivity.class);
                        int fireServiceId = fireServiceInfo.getId();
                        boolean deleted = fireServiceManager.deleteFireServiceInfo(fireServiceId);

                        if (deleted) {
                            startActivity(intent);
                            Toast.makeText(EditFireService.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(EditFireService.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
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
