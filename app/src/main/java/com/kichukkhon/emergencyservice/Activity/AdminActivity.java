package com.kichukkhon.emergencyservice.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kichukkhon.emergencyservice.Database.AdminManager;
import com.kichukkhon.emergencyservice.Database.SharedPreference;
import com.kichukkhon.emergencyservice.Database.Tables.*;
import com.kichukkhon.emergencyservice.Database.DatabaseHelper;
import com.kichukkhon.emergencyservice.R;

public class AdminActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    DatabaseHelper helper;
    AdminManager manager;
    SQLiteDatabase database;
    String name;
    String password;
    SharedPreference sharedPreference;

    public void close() {
        helper.close();
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        etUser = (EditText) findViewById(R.id.txtUserName);
        etPass = (EditText) findViewById(R.id.txtPassword);
        sharedPreference=new SharedPreference(this);

        helper = DatabaseHelper.getInstance(this);
        manager = new AdminManager(AdminActivity.this);
    }

    public void btnLogin(View view) {

        this.open();
        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();

        Cursor cursor = database.query(AdminTable.ADMIN_TABLE, null, null, null, null, null, null);
        //manager.addDefaultAdmin();
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            int id = cursor.getInt(cursor.getColumnIndex(AdminTable._ID));
            name = cursor.getString(cursor.getColumnIndex(AdminTable.COL_USER_NAME));
            password = cursor.getString(cursor.getColumnIndex(AdminTable.COL_PASS_FIELD));


            //cursor.moveToNext();

            if ((user.equals(name)) && (pass.equals(password))) {
                Toast.makeText(getApplicationContext(), "You can successfully logged in",
                        Toast.LENGTH_LONG).show();
                Intent in = new Intent(AdminActivity.this, MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                sharedPreference.saveLoginStatus(true);
                startActivity(in);
            } else {
                Toast.makeText(getApplicationContext(), "Your ID or PASSWORD is incorrect.\n Please try again",
                        Toast.LENGTH_LONG).show();
                etUser.getText().clear();
                etPass.getText().clear();
            }

            this.close();
            database.close();
        }
    }
}
