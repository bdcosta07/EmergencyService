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
        sqLiteDatabase.execSQL("INSERT INTO "+AdminTable.ADMIN_TABLE+"("+AdminTable.COL_USER_NAME+","
                +AdminTable.COL_PASS_FIELD
        +") VALUES ('Admin','admin')");

        //default area values
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Dhanmondi')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Mohammadpur')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Mirpur')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Farmgate')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Monipuripara')");
        sqLiteDatabase.execSQL("INSERT INTO " + AreaEntry.AREA_TABLE + "("+AreaEntry.COL_NAME
                +") VALUES ('Rajabazaar')");


        //default ambulance services (for Dhanmondi)
        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 1,'Ahmed Medical Centre Ltd.','House # 71, Road # 15-A, (New), Dhanmondi C/A','+88028113628')");

        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 1,'Women And Children’S Hospital Pvt.Ltd','House # 48/6, Road # 9/A Dhanmondi, Dhaka','+88029115458')");

        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 1,'Trauma & Orthopaedic Hospital','House # 17, Road # 28(Old),15(New) Dhanmondi R/A, Dhaka','+88029113694')");

        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 1,'The Specialized Hospital (Pvt) Ltd.','House # 40, Road # 10/A,Dhanmondi R/A, Dhaka','+88029118523')");

        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 1,'Saphena Hospital','111, DIT Road, Malibaag, Dhaka (House # 27, Road #, Dhanmondi, Dhaka','+88029121832')");

        //default ambulance services (for Mohammadpur)
        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 2,'Crescent Hospital & Diagnostic Complex Ltd.','22/2, Babor Road, Mohammadpur','+88029117524')");

        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 2,'Medi Prime Orthopaedic Hospital','1/9, Humayan Road, College Gate,Mohammadpur, Dhaka-1207','+88029139226')");

        //default ambulance services (for Farmgate)
        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 4,'Al- Rajhi Hospital','12, Farmgate. Dhaka -1215','+88029117775')");

        sqLiteDatabase.execSQL("INSERT INTO " + AmbulanceEntry.AMBULANCE_TABLE +
                "("+ AmbulanceEntry.COL_AREA_ID +","
                +AmbulanceEntry.COL_ORGANIZATION_NAME+","
                +AmbulanceEntry.COL_ADDRESS+","
                +AmbulanceEntry.COL_PHONE_NO
                +") VALUES ( 4,'Brain & Maind Hospital Ltd.','149/A, Airport Road, Farmgate, Baityl Shoraf Mosque Complex','+88028120710')");


        //Default Fire Services
        sqLiteDatabase.execSQL("INSERT INTO " + FireEntry.FIRE_SERVICE_TABLE +
                "("+ FireEntry.COL_AREA_ID +","
                +FireEntry.COL_ADDRESS+","
                +FireEntry.COL_PHONE_NO
                +") VALUES ( 3,'Mirpur, Dhaka','01730002229')");

        sqLiteDatabase.execSQL("INSERT INTO " + FireEntry.FIRE_SERVICE_TABLE +
                "("+ FireEntry.COL_AREA_ID +","
                +FireEntry.COL_ADDRESS+","
                +FireEntry.COL_PHONE_NO
                +") VALUES ( 2,'Mohammadpur, Dhaka','01730002227')");

        sqLiteDatabase.execSQL("INSERT INTO " + FireEntry.FIRE_SERVICE_TABLE +
                "("+ FireEntry.COL_AREA_ID +","
                +FireEntry.COL_ADDRESS+","
                +FireEntry.COL_PHONE_NO
                +") VALUES ( 4,'Tejgaon, Farmgate, Dhaka','01730002226')");

        //default Police Station
        sqLiteDatabase.execSQL("INSERT INTO " + PoliceEntry.POLICE_TABLE +
                "("+ PoliceEntry.COL_AREA_ID +","
                +PoliceEntry.COL_STATION_NAME+","
                +PoliceEntry.COL_ADDRESS+","
                +PoliceEntry.COL_PHONE_NO+","
                +PoliceEntry.COL_PHONE_NO_OC
                +") VALUES ( 4,'Tejgaon Police Station','56, Tejkunipara, Farmgate. Dhaka -1215','029119467','01713373180')");

        sqLiteDatabase.execSQL("INSERT INTO " + PoliceEntry.POLICE_TABLE +
                "("+ PoliceEntry.COL_AREA_ID +","
                +PoliceEntry.COL_STATION_NAME+","
                +PoliceEntry.COL_ADDRESS+","
                +PoliceEntry.COL_PHONE_NO+","
                +PoliceEntry.COL_PHONE_NO_OC
                +") VALUES ( 2,'Mohammadpur Police Station','Mohammadpur Housing State, Block# E Dhaka','029119960','01713373182')");

        sqLiteDatabase.execSQL("INSERT INTO " + PoliceEntry.POLICE_TABLE +
                "("+ PoliceEntry.COL_AREA_ID +","
                +PoliceEntry.COL_STATION_NAME+","
                +PoliceEntry.COL_ADDRESS+","
                +PoliceEntry.COL_PHONE_NO+","
                +PoliceEntry.COL_PHONE_NO_OC
                +") VALUES ( 2,'Adabar Police Station','115/A Adabar, Mohammadpur, Dhaka','029119962','01713373180')");

        sqLiteDatabase.execSQL("INSERT INTO " + PoliceEntry.POLICE_TABLE +
                "("+ PoliceEntry.COL_AREA_ID +","
                +PoliceEntry.COL_STATION_NAME+","
                +PoliceEntry.COL_ADDRESS+","
                +PoliceEntry.COL_PHONE_NO+","
                +PoliceEntry.COL_PHONE_NO_OC
                +") VALUES ( 1,'Dhanmondi Police Station','House# 41/A, Road# 6, Dhanmondi, Dhaka','028631941','01713373126')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
