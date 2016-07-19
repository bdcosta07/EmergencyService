package com.kichukkhon.emergencyservice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kichukkhon.emergencyservice.Class.AmbulanceInfo;
import com.kichukkhon.emergencyservice.Database.Tables.*;

import java.util.ArrayList;

/**
 * Created by Bridget on 7/14/2016.
 */
public class AmbulanceManager {
    private AmbulanceInfo ambulanceInfo;
    private DatabaseHelper helper;
    SQLiteDatabase database;

    public AmbulanceManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public boolean addAmbulanceInfo(AmbulanceInfo ambulanceInfo) {
        this.open();

        ContentValues contentValues = new ContentValues();

        //contentValues.put(AmbulanceEntry._ID,ambulanceInfo.getId());
        contentValues.put(AmbulanceEntry.COL_AREA_ID, ambulanceInfo.getAreaId());
        contentValues.put(AmbulanceEntry.COL_ORGANIZATION_NAME, ambulanceInfo.getOrgName());
        contentValues.put(AmbulanceEntry.COL_ADDRESS, ambulanceInfo.getAddress());
        contentValues.put(AmbulanceEntry.COL_PHONE_NO, ambulanceInfo.getPhoneNo());

        long inserted = database.insert(AmbulanceEntry.AMBULANCE_TABLE, null, contentValues);

        this.close();
        database.close();

        if (inserted > 0)
            return true;
        else return false;
    }

    public ArrayList<AmbulanceInfo> getAllAmbulanceInfo() {
        this.open();

        ArrayList<AmbulanceInfo> ambulanceInfoList = new ArrayList<>();

        Cursor cursor = database.query(AmbulanceEntry.AMBULANCE_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(AmbulanceEntry._ID));
                int areaId = cursor.getInt(cursor.getColumnIndex(AmbulanceEntry.COL_AREA_ID));
                String orgName = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_ORGANIZATION_NAME));
                String address = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_ADDRESS));
                String phoneNo = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_PHONE_NO));

                ambulanceInfo = new AmbulanceInfo(id, areaId, orgName, address, phoneNo);
                ambulanceInfoList.add(ambulanceInfo);
                cursor.moveToNext();

            }
            this.close();
            database.close();
        }
        return ambulanceInfoList;
    }

    public AmbulanceInfo getAmbulanceInfo(int id) {
        this.open();

        AmbulanceInfo ambulanceInfo;
        Cursor cursor = database.query(AmbulanceEntry.AMBULANCE_TABLE,
                new String[]
                        {AmbulanceEntry._ID, AmbulanceEntry.COL_ORGANIZATION_NAME, AmbulanceEntry.COL_ADDRESS, AmbulanceEntry.COL_PHONE_NO, AmbulanceEntry.COL_AREA_ID},
                AmbulanceEntry._ID + "= " + id,
                null, null, null, null);

        cursor.moveToFirst();
        int mId = cursor.getInt(cursor.getColumnIndex(AmbulanceEntry._ID));
        int areaId = cursor.getInt(cursor.getColumnIndex(AmbulanceEntry.COL_AREA_ID));
        String orgName = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_ORGANIZATION_NAME));
        String address = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_ADDRESS));
        String phoneNo = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_PHONE_NO));

        ambulanceInfo = new AmbulanceInfo(mId, areaId, orgName, address, phoneNo);
        this.close();
        return ambulanceInfo;
    }

    public boolean updateAmbulanceInfo(long id, AmbulanceInfo ambulanceInfo) {
        this.open();
        ContentValues cv = new ContentValues();
        cv.put(AmbulanceEntry.COL_AREA_ID, ambulanceInfo.getAreaId());
        cv.put(AmbulanceEntry.COL_ORGANIZATION_NAME, ambulanceInfo.getOrgName());
        cv.put(AmbulanceEntry.COL_ADDRESS, ambulanceInfo.getAddress());
        cv.put(AmbulanceEntry.COL_PHONE_NO, ambulanceInfo.getPhoneNo());

        int updated = database.update(AmbulanceEntry.AMBULANCE_TABLE, cv,
                AmbulanceEntry._ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean deleteAmbulanceInfo(int id) {

        this.open();
        int deleted = database.delete(AmbulanceEntry.AMBULANCE_TABLE,
                AmbulanceEntry._ID + " = " + id, null);

        this.close();
        if (deleted > 0) {
            return true;
        } else
            return false;
    }

    public ArrayList<AmbulanceInfo> getAmbulanceInfoByAreaId(int areaId){
        this.open();

        ArrayList<AmbulanceInfo> ambulanceInfoList = new ArrayList<>();


        Cursor cursor = database.query(AmbulanceEntry.AMBULANCE_TABLE,
                                        null,
                                        AmbulanceEntry.COL_AREA_ID+" = "+areaId, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(AmbulanceEntry._ID));
                int areaId2 = cursor.getInt(cursor.getColumnIndex(AmbulanceEntry.COL_AREA_ID));
                String orgName = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_ORGANIZATION_NAME));
                String address = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_ADDRESS));
                String phoneNo = cursor.getString(cursor.getColumnIndex(AmbulanceEntry.COL_PHONE_NO));

                ambulanceInfo = new AmbulanceInfo(id, areaId2, orgName, address, phoneNo);
                ambulanceInfoList.add(ambulanceInfo);
                cursor.moveToNext();

            }
            this.close();
            database.close();
        }
        return ambulanceInfoList;
    }
}

