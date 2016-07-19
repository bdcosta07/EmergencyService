package com.kichukkhon.emergencyservice.Database;

import android.provider.BaseColumns;

/**
 * Created by Bridget on 7/14/2016.
 */
public class Tables {
    public final static class AdminTable implements BaseColumns {
        public static final String ADMIN_TABLE = "admin_table";
        public static final String COL_USER_NAME = "admin_name";
        public static final String COL_PASS_FIELD = "password";
    }


    public final static class AmbulanceEntry implements BaseColumns {
        public static final String AMBULANCE_TABLE = "ambulance";

        public static final String COL_AREA_ID = "area_id";
        public static final String COL_ORGANIZATION_NAME = "organizationName";
        public static final String COL_ADDRESS = "address";
        public static final String COL_PHONE_NO = "phone_no";

    }

    public final static class PoliceEntry implements BaseColumns {
        public static final String POLICE_TABLE = "police";

        public static final String COL_AREA_ID = "area_id";
        public static final String COL_STATION_NAME = "stationName";
        public static final String COL_ADDRESS = "address";
        public static final String COL_PHONE_NO = "phone_no";
        public static final String COL_PHONE_NO_OC = "phone_no_oc";

    }

    public final static class FireEntry implements BaseColumns {
        public static final String FIRE_SERVICE_TABLE = "fire_service";

        public static final String COL_AREA_ID = "area_id";
        public static final String COL_ADDRESS = "address";
        public static final String COL_PHONE_NO = "phone_no";

    }

    public final static class AreaEntry implements BaseColumns {
        public static final String AREA_TABLE = "area_list";

        public static final String COL_NAME = "area_name";
    }
}
