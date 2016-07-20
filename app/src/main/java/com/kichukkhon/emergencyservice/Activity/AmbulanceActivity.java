package com.kichukkhon.emergencyservice.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.kichukkhon.emergencyservice.Adapter.AmbulanceAdapter;
import com.kichukkhon.emergencyservice.Class.AmbulanceInfo;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.Utils;
import com.kichukkhon.emergencyservice.Create.CreateAmbulance;
import com.kichukkhon.emergencyservice.Database.AmbulanceManager;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.ESBaseActivity;
import com.kichukkhon.emergencyservice.R;

import java.util.ArrayList;
import java.util.List;

public class AmbulanceActivity extends ESBaseActivity {
    ListView lvAmbulance;
    Spinner spinnerArea;
    ArrayList<AmbulanceInfo> ambulanceList;
    AmbulanceAdapter adapter;
    AmbulanceManager manager;
    AreaManager areaManager;
    SharedPreference preference;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        lvAmbulance = (ListView) findViewById(R.id.lvAmbulance);
        spinnerArea = (Spinner) findViewById(R.id.spinnerArea);
        Button btnCreate = (Button) findViewById(R.id.btnCreateAmbulance);

        preference = new SharedPreference(this);

        if(!preference.isLoggedIn())
            btnCreate.setVisibility(View.GONE);

        ambulanceList = new ArrayList<AmbulanceInfo>();
        manager = new AmbulanceManager(AmbulanceActivity.this);
        areaManager = new AreaManager(AmbulanceActivity.this);

        List<Areas> areaList = areaManager.getAllAreas();

        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);

        //selected area id from pref
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String preferredAreaId = sharedPref.getString("preferred_area_id", "1");

        int selectedAreaId = Integer.parseInt(preferredAreaId);
        spinnerArea.setSelection(Utils.getIndex(spinnerArea, selectedAreaId));
        getData(selectedAreaId);


        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int areaId = ((Areas) spinnerArea.getSelectedItem()).getAreaId();
                getData(areaId);

            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getData(int areaId) {
        ambulanceList = manager.getAmbulanceInfoByAreaId(areaId);
        adapter = new AmbulanceAdapter(this, ambulanceList);
        adapter.notifyDataSetChanged();
        lvAmbulance.setAdapter(adapter);
    }


    public void btnCreateAmbulance(View view) {
        Intent intent = new Intent(AmbulanceActivity.this, CreateAmbulance.class);
        startActivity(intent);
    }
}
