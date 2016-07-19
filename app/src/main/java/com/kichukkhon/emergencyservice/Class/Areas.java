package com.kichukkhon.emergencyservice.Class;

/**
 * Created by Bridget on 7/15/2016.
 */
public class Areas {
    private int areaId;
    private String areaName;

    public Areas(String areaName) {
        this.areaName = areaName;
    }

    public Areas(int areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return areaName;
    }
}
