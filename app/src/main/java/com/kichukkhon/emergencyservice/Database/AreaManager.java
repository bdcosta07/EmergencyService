package com.kichukkhon.emergencyservice.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kichukkhon.emergencyservice.Class.Areas;
import com.kichukkhon.emergencyservice.Database.Tables.*;

import java.util.ArrayList;

/**
 * Created by Bridget on 7/15/2016.
 */
public class AreaManager {
    private Areas areas;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public AreaManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public ArrayList<Areas> getAllAreas() {
        this.open();

        ArrayList<Areas> areaList = new ArrayList<>();

        Cursor cursor = database.query(AreaEntry.AREA_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(AreaEntry._ID));
                String areaName = cursor.getString(cursor.getColumnIndex(AreaEntry.COL_NAME));

                areas = new Areas(id, areaName);
                areaList.add(areas);
                cursor.moveToNext();

            }
            this.close();
            database.close();
        }
        return areaList;
    }

    public Areas getAreaById(int id){
        this.open();

        Areas areas;
        Cursor cursor=database.query(AreaEntry.AREA_TABLE,new String[]
                        {AreaEntry._ID,AreaEntry.COL_NAME},
                AreaEntry._ID+"= "+id,null,null,null,null);

        cursor.moveToFirst();
        int mId=cursor.getInt(cursor.getColumnIndex(AreaEntry._ID));
        String areaName = cursor.getString(cursor.getColumnIndex(AreaEntry.COL_NAME));

        areas=new Areas(mId,areaName);
        this.close();
        return areas;
    }


}
