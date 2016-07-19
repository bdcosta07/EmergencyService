package com.kichukkhon.emergencyservice.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    Spinner spinnerArea;
    AreaManager manager;
    List<Areas> areaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spinnerArea=(Spinner)findViewById(R.id.spinnerArea);
        areaList=new ArrayList<>();
        manager=new AreaManager(SettingsActivity.this);
        areaList= manager.getAllAreas();

        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerArea.setAdapter(dataAdapter);
    }

    public void btnAreaSave(View view) {
    }
}
