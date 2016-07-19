package com.kichukkhon.emergencyservice.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kichukkhon.emergencyservice.Class.AdminInfo;
import com.kichukkhon.emergencyservice.Database.Tables.*;

import java.util.ArrayList;

/**
 * Created by Bridget on 7/14/2016.
 */
public class AdminManager {
    AdminInfo adminInfo;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public AdminManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public ArrayList<AdminInfo> getAdminInfo() {
        this.open();
        ArrayList<AdminInfo> admin = new ArrayList<>();

        Cursor cursor = database.query(AdminTable.ADMIN_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(AdminTable._ID));
                String name = cursor.getString(cursor.getColumnIndex(AdminTable.COL_USER_NAME));
                String password = cursor.getString(cursor.getColumnIndex(AdminTable.COL_PASS_FIELD));

                adminInfo = new AdminInfo(id,name,password);
                admin.add(adminInfo);
                cursor.moveToNext();
            }
            this.close();
            database.close();

        }
        return admin;
    }
}
