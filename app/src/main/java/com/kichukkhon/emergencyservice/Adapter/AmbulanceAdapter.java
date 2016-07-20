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

import com.kichukkhon.emergencyservice.Class.AmbulanceInfo;
import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.EditActivity.EditAmbulance;
import com.kichukkhon.emergencyservice.R;

import java.util.ArrayList;

/**
 * Created by Bridget on 7/15/2016.
 */
public class AmbulanceAdapter extends ArrayAdapter {
    static private class ViewHolder {
        TextView tvAreaName;
        TextView tvOrgName;
        TextView tvAddress;
        TextView tvPhoneNo;
        ImageButton btnEdit, btnCall;
        int id;
    }

    ArrayList<AmbulanceInfo> ambulanceList;
    Context context;
    //int id;

    public AmbulanceAdapter(Context context, ArrayList<AmbulanceInfo> ambulanceList) {
        super(context, R.layout.row_ambulance, ambulanceList);
        this.ambulanceList = ambulanceList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.row_ambulance, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvAreaName = (TextView) rowView.findViewById(R.id.tvArea);
            viewHolder.tvOrgName = (TextView) rowView.findViewById(R.id.tvOrgName);
            viewHolder.tvAddress = (TextView) rowView.findViewById(R.id.tvAddress);
            viewHolder.tvPhoneNo = (TextView) rowView.findViewById(R.id.tvPhoneNo);
            viewHolder.btnEdit = (ImageButton) rowView.findViewById(R.id.btnEdit);
            viewHolder.btnCall = (ImageButton) rowView.findViewById(R.id.btnCall);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.id = ambulanceList.get(position).getId();

        AreaManager areaManager = new AreaManager(context);
        int areaId = ambulanceList.get(position).getAreaId();
        Areas area = areaManager.getAreaById(areaId);

        viewHolder.tvAreaName.setText(area != null ? area.getAreaName() : "");
        viewHolder.tvOrgName.setText(ambulanceList.get(position).getOrgName());
        viewHolder.tvAddress.setText(ambulanceList.get(position).getAddress());
        viewHolder.tvPhoneNo.setText(ambulanceList.get(position).getPhoneNo());

        viewHolder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = viewHolder.tvPhoneNo.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNo));
                context.startActivity(callIntent);
            }
        });

        SharedPreference preference = new SharedPreference(context);

        if(!preference.isLoggedIn())
            viewHolder.btnEdit.setVisibility(View.INVISIBLE);


        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EditAmbulance.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", viewHolder.id);
                context.startActivity(intent);

            }
        });

        return rowView;
    }

}
