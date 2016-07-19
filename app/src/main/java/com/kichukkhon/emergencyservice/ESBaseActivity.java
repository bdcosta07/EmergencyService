package com.kichukkhon.emergencyservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.kichukkhon.emergencyservice.Activity.AdminActivity;
import com.kichukkhon.emergencyservice.Activity.MainActivity;
import com.kichukkhon.emergencyservice.Activity.SettingsActivity;
import com.kichukkhon.emergencyservice.Database.SharedPreference;

/**
 * Created by Bridget on 7/18/2016.
 */
public abstract class ESBaseActivity extends AppCompatActivity {
    SharedPreference preference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preference = new SharedPreference(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem adminItem = menu.findItem(R.id.menu_admin);
        MenuItem logoutItem = menu.findItem(R.id.menu_logout);
        if (preference.isLoggedIn()) {
            logoutItem.setVisible(true);
            adminItem.setVisible(false);
        } else {
            adminItem.setVisible(true);
            logoutItem.setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_admin:
                startActivity(new Intent(this, AdminActivity.class));
                break;

            case R.id.menu_setting:
                startActivity(new Intent(this, SettingsActivity.class));
                break;

            case R.id.menu_home:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.menu_logout:
                preference.saveLoginStatus(false);
                Toast.makeText(getApplicationContext(), "You are successfully logged out",
                        Toast.LENGTH_LONG).show();
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
