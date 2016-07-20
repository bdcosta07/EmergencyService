package com.kichukkhon.emergencyservice.EditActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kichukkhon.emergencyservice.Activity.PoliceActivity;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.PoliceInfo;
import com.kichukkhon.emergencyservice.Class.Utils;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.PoliceManager;
import com.kichukkhon.emergencyservice.R;

import java.util.List;

public class EditPolice extends AppCompatActivity {
    Spinner spinnerArea;
    EditText policeStationET;
    EditText phoneNoET;
    EditText phoneNoOcET;
    PoliceInfo policeInfo;
    PoliceManager policeManager;
    AreaManager areaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_police);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_police);
        spinnerArea = (Spinner) findViewById(R.id.spinnerArea);
        policeStationET = (EditText) findViewById(R.id.txtPoliceStation);
        phoneNoET = (EditText) findViewById(R.id.txtPhoneNo);
        phoneNoOcET = (EditText) findViewById(R.id.txtPhoneNoOc);
        policeManager = new PoliceManager(EditPolice.this);
        areaManager = new AreaManager(EditPolice.this);

        fillViews();
    }

    public void fillViews() {
        int id = getIntent().getIntExtra("id", 0);

        List<Areas> areaList = areaManager.getAllAreas();
        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);

        policeInfo = policeManager.getPoliceInfo(id);

        int selectedAreaId = policeInfo.getAreaId();
        spinnerArea.setSelection(Utils.getIndex(spinnerArea, selectedAreaId));
        policeStationET.setText(policeInfo.getPoliceStation());
        phoneNoET.setText(policeInfo.getPhoneNo());
        phoneNoOcET.setText(policeInfo.getPhoneNoOc());
    }

    public void btnDeletePolice(View view) {
        Intent intent = new Intent(EditPolice.this, PoliceActivity.class);

        String policeStation = policeStationET.getText().toString();
        String phoneNo = phoneNoET.getText().toString();
        String phoneNoOc = phoneNoOcET.getText().toString();
        int areaId = ((Areas) spinnerArea.getSelectedItem()).getAreaId();

        policeInfo.setPoliceStation(policeStation);
        policeInfo.setPhoneNo(phoneNo);
        policeInfo.setPhoneNoOc(phoneNoOc);
        policeInfo.setAreaId(areaId);

        policeManager = new PoliceManager(EditPolice.this);
        int id = policeInfo.getId();

        boolean updated = policeManager.updatePoliceInfo(id, policeInfo);
        if (updated) {
            startActivity(intent);
            Toast.makeText(EditPolice.this, "Data Updated", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(EditPolice.this, "Data not Updated", Toast.LENGTH_SHORT).show();

    }

}
