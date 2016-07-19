package com.kichukkhon.emergencyservice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kichukkhon.emergencyservice.Class.FireServiceInfo;
import com.kichukkhon.emergencyservice.Database.Tables.*;

import java.util.ArrayList;

/**
 * Created by Bridget on 7/14/2016.
 */
public class FireServiceManager {
    private FireServiceInfo fireServiceInfo;
    private DatabaseHelper helper;
    SQLiteDatabase database;

    public FireServiceManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public boolean addFireServiceInfo(FireServiceInfo fireServiceInfo) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FireEntry.COL_AREA_ID, fireServiceInfo.getAreaId());
        contentValues.put(FireEntry.COL_ADDRESS, fireServiceInfo.getAddress());
        contentValues.put(FireEntry.COL_PHONE_NO, fireServiceInfo.getPhoneNo());

        /*long inserted = database.insert(FireEntry.FIRE_SERVICE_TABLE, null, contentValues);*/
        long inserted = database.insert(FireEntry.FIRE_SERVICE_TABLE, null, contentValues);

        this.close();
        database.close();

        if (inserted > 0)
            return true;
        else return false;
    }

    public ArrayList<FireServiceInfo> getAllFireServiceInfo() {
        this.open();

        ArrayList<FireServiceInfo> fireServiceInfoList = new ArrayList<>();

        Cursor cursor = database.query(Tables.FireEntry.FIRE_SERVICE_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(FireEntry._ID));
                int areaId = cursor.getInt(cursor.getColumnIndex(FireEntry.COL_AREA_ID));
                String address = cursor.getString(cursor.getColumnIndex(FireEntry.COL_ADDRESS));
                String phoneNo = cursor.getString(cursor.getColumnIndex(FireEntry.COL_PHONE_NO));

                fireServiceInfo = new FireServiceInfo(id, areaId, address, phoneNo);
                fireServiceInfoList.add(fireServiceInfo);
                cursor.moveToNext();

            }
            this.close();
            database.close();
        }
        return fireServiceInfoList;
    }

    public FireServiceInfo getFireServiceInfo(int id) {
        this.open();

        FireServiceInfo fireServiceInfo;
        Cursor cursor = database.query(FireEntry.FIRE_SERVICE_TABLE, new String[]
                        {FireEntry._ID,FireEntry.COL_ADDRESS, FireEntry.COL_PHONE_NO, Tables.FireEntry.COL_AREA_ID},
                Tables.FireEntry._ID + "= " + id, null, null, null, null);

        cursor.moveToFirst();
        int mId = cursor.getInt(cursor.getColumnIndex(FireEntry._ID));
        int areaId = cursor.getInt(cursor.getColumnIndex(FireEntry.COL_AREA_ID));
        String address = cursor.getString(cursor.getColumnIndex(FireEntry.COL_ADDRESS));
        String phoneNo = cursor.getString(cursor.getColumnIndex(FireEntry.COL_PHONE_NO));

        fireServiceInfo = new FireServiceInfo(mId, areaId, address, phoneNo);
        this.close();
        return fireServiceInfo;
    }

    public boolean updateFireServiceInfo(int id, FireServiceInfo FireServiceInfo) {
        this.open();
        ContentValues cv = new ContentValues();
        cv.put(FireEntry.COL_AREA_ID, FireServiceInfo.getAreaId());
        cv.put(FireEntry.COL_ADDRESS, FireServiceInfo.getAddress());
        cv.put(FireEntry.COL_PHONE_NO, FireServiceInfo.getPhoneNo());

        int updated = database.update(FireEntry.FIRE_SERVICE_TABLE, cv,
                FireEntry._ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean deleteFireServiceInfo(int id) {

        this.open();
        int deleted = database.delete(FireEntry.FIRE_SERVICE_TABLE,
                FireEntry._ID + " = " + id, null);

        this.close();
        if (deleted > 0) {
            return true;
        } else
            return false;
    }

    public ArrayList<FireServiceInfo> getFireInfoByAreaId(int areaId){
        this.open();

        ArrayList<FireServiceInfo> fireServiceList = new ArrayList<>();


        Cursor cursor = database.query(FireEntry.FIRE_SERVICE_TABLE,
                null,
                FireEntry.COL_AREA_ID+" = "+areaId, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(FireEntry._ID));
                int areaId2 = cursor.getInt(cursor.getColumnIndex(FireEntry.COL_AREA_ID));
                String address = cursor.getString(cursor.getColumnIndex(FireEntry.COL_ADDRESS));
                String phoneNo = cursor.getString(cursor.getColumnIndex(FireEntry.COL_PHONE_NO));

                fireServiceInfo = new FireServiceInfo(id, areaId2, address, phoneNo);
                fireServiceList.add(fireServiceInfo);
                cursor.moveToNext();

            }
            this.close();
            database.close();
        }
        return fireServiceList;
    }
}
