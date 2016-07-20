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
import com.kichukkhon.emergencyservice.Class.FireServiceInfo;
import com.kichukkhon.emergencyservice.Database.AreaManager;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.EditActivity.EditFireService;
import com.kichukkhon.emergencyservice.R;

import java.util.ArrayList;

/**
 * Created by HP on 16/7/2016.
 */
public class FireServiceAdapter extends ArrayAdapter {
    static private class ViewHolder {
        TextView tvAreaName;
        TextView tvAddress;
        TextView tvPhoneNo;
        ImageButton btnEdit, btnCall;
        int id;
    }

    ArrayList<FireServiceInfo> fireServiceList;
    Context context;
    //int id;

    public FireServiceAdapter(Context context, ArrayList<FireServiceInfo> fireServiceList) {
        super(context, R.layout.row_fire_service, fireServiceList);
        this.fireServiceList = fireServiceList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.row_fire_service, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvAreaName = (TextView) rowView.findViewById(R.id.tvArea);
            viewHolder.tvAddress = (TextView) rowView.findViewById(R.id.tvAddress);
            viewHolder.tvPhoneNo = (TextView) rowView.findViewById(R.id.tvPhoneNo);
            viewHolder.btnEdit = (ImageButton) rowView.findViewById(R.id.btnEdit);
            viewHolder.btnCall = (ImageButton) rowView.findViewById(R.id.btnCall);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.id = fireServiceList.get(position).getId();

        AreaManager areaManager = new AreaManager(context);
        int areaId = fireServiceList.get(position).getAreaId();
        Areas area = areaManager.getAreaById(areaId);

        viewHolder.tvAreaName.setText(area != null ? area.getAreaName() : "");
        viewHolder.tvAddress.setText(fireServiceList.get(position).getAddress());
        viewHolder.tvPhoneNo.setText(fireServiceList.get(position).getPhoneNo());

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
                Intent intent = new Intent(context, EditFireService.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", viewHolder.id);
                context.startActivity(intent);

            }
        });

        return rowView;
    }
}