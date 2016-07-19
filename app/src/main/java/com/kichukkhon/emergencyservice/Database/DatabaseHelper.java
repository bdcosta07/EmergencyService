package com.kichukkhon.emergencyservice.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.kichukkhon.emergencyservice.Database.Tables.*;


/**
 * Created by Bridget on 7/14/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper dbInstance;

    public static final String DATABASE_NAME = "emergency.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static DatabaseHelper getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DatabaseHelper(context);
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_ADMIN_TABLE="CREATE TABLE " + AdminTable.ADMIN_TABLE
                +"( "+ AdminTable._ID+" INTEGER PRIMARY KEY, "
                +AdminTable.COL_USER_NAME+" TEXT, "
                +AdminTable.COL_PASS_FIELD+ " TEXT)";

        final String CREATE_AMBULANCE_TABLE="CREATE TABLE " + AmbulanceEntry.AMBULANCE_TABLE
                +"( "+ AmbulanceEntry._ID+" INTEGER PRIMARY KEY, "
                +AmbulanceEntry.COL_AREA_ID+" INTEGER, "
                +AmbulanceEntry.COL_ADDRESS+ " TEXT, "
                +AmbulanceEntry.COL_ORGANIZATION_NAME+ " TEXT,"
                +AmbulanceEntry.COL_PHONE_NO+" TEXT)";

        final String CREATE_POLICE_TABLE="CREATE TABLE " + PoliceEntry.POLICE_TABLE
                +"( "+ PoliceEntry._ID+" INTEGER PRIMARY KEY, "
                +PoliceEntry.COL_AREA_ID+" INTEGER, "
                +PoliceEntry.COL_STATION_NAME+" TEXT, "
                +PoliceEntry.COL_ADDRESS+ " TEXT, "
                +PoliceEntry.COL_PHONE_NO+" TEXT, "
                +PoliceEntry.COL_PHONE_NO_OC+" TEXT)";

        final String CREATE_FIRE_SERVICE_TABLE="CREATE TABLE " + FireEntry.FIRE_SERVICE_TABLE
                +"( "+ FireEntry._ID+" INTEGER PRIMARY KEY, "
                +FireEntry.COL_AREA_ID+" INTEGER, "
                +FireEntry.COL_ADDRESS+ " TEXT, "
                +FireEntry.COL_PHONE_NO+" TEXT)";

        final String CREATE_AREA_LIST_TABLE="CREATE TABLE " +  AreaEntry.AREA_TABLE
                +"( "+ AreaEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +AreaEntry.COL_NAME+" TEXT )";


        sqLiteDatabase.execSQL(CREATE_AMBULANCE_TABLE);
        sqLiteDatabase.execSQL(CREATE_POLICE_TABLE);
        sqLiteDatabase.execSQL(CREATE_FIRE_SERVICE_TABLE);
        sqLiteDatabase.execSQL(CREATE_ADMIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_AREA_LIST_TABLE);

        //default admin value
        sqLiteDatabase.execSQL("INSERT INTO "+AdminTable.ADMIN_TABLE+"("+AdminTable.COL_USER_NAME+","+AdminTable.COL_PASS_FIELD
        +") VALUES ('Admin','admin')");

        //default area values
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Kawranbazaar')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Banglamotor')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('New Eskaton')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Farmgate')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Monipuripara')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Rajabazaar')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
