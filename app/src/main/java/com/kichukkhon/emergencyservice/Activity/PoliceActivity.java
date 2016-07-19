package com.kichukkhon.emergencyservice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.kichukkhon.emergencyservice.Adapter.PoliceAdapter;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.PoliceInfo;
import com.kichukkhon.emergencyservice.Create.CreatePolice;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.PoliceManager;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.ESBaseActivity;
import com.kichukkhon.emergencyservice.R;

import java.util.ArrayList;
import java.util.List;

public class PoliceActivity extends ESBaseActivity {
    ListView lvPoliceStation;
    Spinner spinnerArea;
    ArrayList<PoliceInfo> policeList;
    PoliceAdapter policeAdapter;
    PoliceManager policeManager;
    AreaManager areaManager;
    SharedPreference preference;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lvPoliceStation=(ListView)findViewById(R.id.lvPolice);
        spinnerArea=(Spinner)findViewById(R.id.spinnerArea);

        policeList=new ArrayList<PoliceInfo>();
        policeManager=new PoliceManager(PoliceActivity.this);
        areaManager=new AreaManager(PoliceActivity.this);
        Button btnCreate = (Button) findViewById(R.id.btnCreatePolice);

        preference = new SharedPreference(this);

        if(!preference.isLoggedIn())
            btnCreate.setVisibility(View.GONE);

        List<Areas> areaList= areaManager.getAllAreas();

        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);

        int selectedAreaId=1; //has to be fetched from pref
        getData(selectedAreaId);  spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int areaId = ((Areas) spinnerArea.getSelectedItem()).getAreaId();
                getData(areaId);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void getData(int areaId){
        policeList=policeManager.getPoliceInfoByAreaId(areaId);
        policeAdapter=new PoliceAdapter(this,policeList);
        policeAdapter.notifyDataSetChanged();
        lvPoliceStation.setAdapter(policeAdapter);
    }
    public void btnCreatePoliceStation(View view) {

        Intent intent = new Intent(PoliceActivity.this, CreatePolice.class);
        startActivity(intent);
    }
}
