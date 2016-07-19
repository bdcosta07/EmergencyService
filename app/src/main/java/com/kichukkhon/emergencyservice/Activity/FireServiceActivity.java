package com.kichukkhon.emergencyservice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.kichukkhon.emergencyservice.Adapter.FireServiceAdapter;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.FireServiceInfo;
import com.kichukkhon.emergencyservice.Create.CreateFireService;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.FireServiceManager;
import com.kichukkhon.emergencyservice.ESBaseActivity;
import com.kichukkhon.emergencyservice.R;

import java.util.ArrayList;
import java.util.List;

public class FireServiceActivity extends ESBaseActivity {

    ListView lvFireService;
    Spinner spinnerArea;
    ArrayList<FireServiceInfo> fireServiceList;
    FireServiceAdapter fireServiceAdapter;
    FireServiceManager fireServiceManager;
    AreaManager areaManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_service);

        lvFireService=(ListView)findViewById(R.id.lvFireService);
        spinnerArea=(Spinner)findViewById(R.id.spinnerArea);

        fireServiceList=new ArrayList<FireServiceInfo>();
        fireServiceManager=new FireServiceManager(FireServiceActivity.this);
        areaManager=new AreaManager(FireServiceActivity.this);

        List<Areas> areaList= areaManager.getAllAreas();

        ArrayAdapter<Areas> dataAdapter = new ArrayAdapter<Areas>(this, android.R.layout.simple_spinner_item, areaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerArea.setAdapter(dataAdapter);

        int selectedAreaId=1; //has to be fetched from pref
        getData(selectedAreaId);


        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
        fireServiceList=fireServiceManager.getFireInfoByAreaId(areaId);
        fireServiceAdapter=new FireServiceAdapter(this,fireServiceList);
        fireServiceAdapter.notifyDataSetChanged();
        lvFireService.setAdapter(fireServiceAdapter);
    }


    public void btnCreateFireService(View view) {
        Intent intent=new Intent(FireServiceActivity.this, CreateFireService.class);
        startActivity(intent);
    }
}
