package com.kichukkhon.emergencyservice.Class;

/**
 * Created by Bridget on 7/14/2016.
 */
public class PoliceInfo {
    private int id;
    private int areaId;
    private String policeStation;
    private String phoneNo;
    private String phoneNoOc;

    public PoliceInfo(int id, int areaId, String policeStation, String phoneNo, String phoneNoOc) {
        this.id = id;
        this.areaId = areaId;
        this.policeStation = policeStation;
        this.phoneNo = phoneNo;
        this.phoneNoOc = phoneNoOc;
    }

    public PoliceInfo() {
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String thana) {
        this.policeStation = thana;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNoOc() {
        return phoneNoOc;
    }

    public void setPhoneNoOc(String phoneNoOc) {
        this.phoneNoOc = phoneNoOc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
