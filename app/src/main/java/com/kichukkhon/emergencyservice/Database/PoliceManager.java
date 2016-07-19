package com.kichukkhon.emergencyservice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kichukkhon.emergencyservice.Class.PoliceInfo;
import com.kichukkhon.emergencyservice.Database.Tables.PoliceEntry;

import java.util.ArrayList;

/**
 * Created by Bridget on 7/14/2016.
 */
public class PoliceManager {
    private PoliceInfo policeInfo;
    private DatabaseHelper helper;
    SQLiteDatabase database;

    public PoliceManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public boolean addPoliceInfo(PoliceInfo policeInfo) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PoliceEntry.COL_AREA_ID, policeInfo.getAreaId());
        contentValues.put(PoliceEntry.COL_STATION_NAME, policeInfo.getPoliceStation());
        contentValues.put(PoliceEntry.COL_PHONE_NO, policeInfo.getPhoneNo());
        contentValues.put(PoliceEntry.COL_PHONE_NO, policeInfo.getPhoneNoOc());

        long inserted = database.insert(PoliceEntry.POLICE_TABLE, null, contentValues);

        this.close();
        database.close();

        if (inserted > 0)
            return true;
        else return false;
    }

    public ArrayList<PoliceInfo> getAllPoliceInfo() {
        this.open();

        ArrayList<PoliceInfo> policeList = new ArrayList<>();

        Cursor cursor = database.query(PoliceEntry.POLICE_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(PoliceEntry._ID));
                int areaId = cursor.getInt(cursor.getColumnIndex(PoliceEntry.COL_AREA_ID));
                String policeStation = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_STATION_NAME));
                String phoneNo= cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_PHONE_NO));
                String phoneNoOc = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_PHONE_NO_OC));

                policeInfo = new PoliceInfo(id, areaId, policeStation, phoneNo, phoneNoOc);
                policeList.add(policeInfo);
                cursor.moveToNext();

            }
            this.close();
            database.close();
        }
        return policeList;
    }

    public PoliceInfo getPoliceInfo(int id) {
        this.open();

        PoliceInfo policeInfo;
        Cursor cursor = database.query(PoliceEntry.POLICE_TABLE, new String[]
                        {PoliceEntry._ID, PoliceEntry.COL_STATION_NAME, PoliceEntry.COL_PHONE_NO, PoliceEntry.COL_PHONE_NO_OC, PoliceEntry.COL_PHONE_NO_OC},
                PoliceEntry._ID + "= " + id, null, null, null, null);

        cursor.moveToFirst();
        int mId = cursor.getInt(cursor.getColumnIndex(PoliceEntry._ID));
        int areaId = cursor.getInt(cursor.getColumnIndex(PoliceEntry.COL_AREA_ID));
        String thana = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_STATION_NAME));
        String phoneNo = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_PHONE_NO));
        String phoneNoOc = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_PHONE_NO_OC));

        policeInfo = new PoliceInfo(mId, areaId, thana, phoneNo, phoneNoOc);
        this.close();
        return policeInfo;
    }

    public boolean updatePoliceInfo(int id, PoliceInfo policeInfo) {
        this.open();
        ContentValues cv = new ContentValues();
        cv.put(PoliceEntry.COL_AREA_ID, policeInfo.getAreaId());
        cv.put(PoliceEntry.COL_STATION_NAME, policeInfo.getPoliceStation());
        cv.put(PoliceEntry.COL_PHONE_NO, policeInfo.getPhoneNo());
        cv.put(PoliceEntry.COL_PHONE_NO, policeInfo.getPhoneNoOc());

        int updated = database.update(PoliceEntry.POLICE_TABLE, cv,
                PoliceEntry._ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean deletePoliceInfo(int id) {

        this.open();
        int deleted = database.delete(PoliceEntry.POLICE_TABLE,
                PoliceEntry._ID + " = " + id, null);

        this.close();
        if (deleted > 0) {
            return true;
        } else
            return false;
    }

    public ArrayList<PoliceInfo> getPoliceInfoByAreaId(int areaId){
        this.open();

        ArrayList<PoliceInfo> policeList = new ArrayList<>();


        Cursor cursor = database.query(PoliceEntry.POLICE_TABLE,
                null,
                PoliceEntry.COL_AREA_ID+" = "+areaId, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(PoliceEntry._ID));
                int areaId2 = cursor.getInt(cursor.getColumnIndex(PoliceEntry.COL_AREA_ID));
                String policeStation = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_STATION_NAME));
                String phoneNo = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_PHONE_NO));
                String phoneNoOc = cursor.getString(cursor.getColumnIndex(PoliceEntry.COL_PHONE_NO_OC));

                policeInfo = new PoliceInfo(id, areaId2, policeStation, phoneNo, phoneNoOc);
                policeList.add(policeInfo);
                cursor.moveToNext();

            }
            this.close();
            database.close();
        }
        return policeList;
    }
}
