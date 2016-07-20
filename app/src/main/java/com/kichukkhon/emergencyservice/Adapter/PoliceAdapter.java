package com.kichukkhon.emergencyservice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Class.PoliceInfo;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.EditActivity.EditPolice;
import com.kichukkhon.emergencyservice.R;

import java.util.ArrayList;

/**
 * Created by HP on 17/7/2016.
 */
public class PoliceAdapter extends ArrayAdapter {
    static private class ViewHolder {
    TextView tvAreaName;
    TextView tvPoliceStation;
    TextView tvPhoneNo;
    TextView tvPhoneNoOc;
    ImageButton btnEdit, btnCall,btnCallOc;
    int id;
}

    ArrayList<PoliceInfo> policeList;
    Context context;
    //int id;

    public PoliceAdapter(Context context, ArrayList<PoliceInfo> policeList) {
        super(context, R.layout.row_police, policeList);
        this.policeList = policeList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.row_police, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvAreaName = (TextView) rowView.findViewById(R.id.tvArea);
            viewHolder.tvPoliceStation = (TextView) rowView.findViewById(R.id.tvThana);
            viewHolder.tvPhoneNo = (TextView) rowView.findViewById(R.id.tvPhoneNo);
            viewHolder.tvPhoneNoOc = (TextView) rowView.findViewById(R.id.tvPhoneNoOc);
            viewHolder.btnEdit = (ImageButton) rowView.findViewById(R.id.btnEdit);
            viewHolder.btnCall = (ImageButton) rowView.findViewById(R.id.btnCall);
            viewHolder.btnCallOc=(ImageButton) rowView.findViewById(R.id.btnCallOc);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.id = policeList.get(position).getId();

        AreaManager areaManager = new AreaManager(context);
        int areaId = policeList.get(position).getAreaId();
        Areas area = areaManager.getAreaById(areaId);

        viewHolder.tvAreaName.setText(area != null ? area.getAreaName() : "");
        viewHolder.tvPoliceStation.setText(policeList.get(position).getPoliceStation());
        viewHolder.tvPhoneNo.setText(policeList.get(position).getPhoneNo()+" (Duty Officer)");
        viewHolder.tvPhoneNoOc.setText(policeList.get(position).getPhoneNoOc()+" (Call OC)");

        viewHolder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = viewHolder.tvPhoneNo.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNo));
                context.startActivity(callIntent);
            }
        });

        viewHolder.btnCallOc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNoOc = viewHolder.tvPhoneNoOc.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNoOc));
                context.startActivity(callIntent);
            }
        });

        SharedPreference preference = new SharedPreference(context);

        if(!preference.isLoggedIn())
            viewHolder.btnEdit.setVisibility(View.INVISIBLE);

        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditPolice.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", viewHolder.id);
                context.startActivity(intent);

            }
        });

        return rowView;
    }
}