package com.kichukkhon.emergencyservice.Class;

import android.widget.Spinner;

/**
 * Created by Ratul on 7/20/2016.
 */
public final class Utils {
    public static int getIndex(Spinner spinner, int id)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (((Areas) spinner.getItemAtPosition(i)).getAreaId()==id){
                index = i;
                break;
            }
        }
        return index;
    }
}
