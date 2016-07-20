package com.kichukkhon.emergencyservice.Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Bridget on 7/17/2016.
 */
public class SharedPreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    //private HashMap<String, String> map;

    private static final String PREFERENCE_NAME = "emergency_service";
    private static final String LOGIN_STATUS_KEY = "ISLOGGEDIN";
    private static final String ALREADY_VISITED = "already_visited";


    public SharedPreference(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

    }

    public void saveLoginStatus(boolean isLoggedIn) {
     /*   map = new HashMap<String, String>();
        if (isLoggedIn)
            map.put(LOGIN_STATUS_KEY, "true");
        else
            map.put(LOGIN_STATUS_KEY, "false");*/
        if (isLoggedIn)
            editor.putBoolean(LOGIN_STATUS_KEY, true);
        else
            editor.putBoolean(LOGIN_STATUS_KEY, false);

        editor.commit();
    }

    public boolean isLoggedIn() {
        boolean preferenceData = sharedPreferences.getBoolean(LOGIN_STATUS_KEY, false);
        return preferenceData;
    }

    public boolean AlreadyVisited() {
        boolean prefData = sharedPreferences.getBoolean(ALREADY_VISITED, false);
        return prefData;
    }

    public void setAlreadyVisitStatus(){
        editor.putBoolean(ALREADY_VISITED, true);

        editor.commit();
    }
}

