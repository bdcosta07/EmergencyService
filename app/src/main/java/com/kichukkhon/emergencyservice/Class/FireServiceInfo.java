package com.kichukkhon.emergencyservice.Class;

/**
 * Created by Bridget on 7/14/2016.
 */
public class FireServiceInfo {
    private int id;
    private int areaId;
    private String address;
    private String phoneNo;

    public FireServiceInfo(int id, int areaId, String address, String phoneNo) {
        this.id = id;
        this.areaId = areaId;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public FireServiceInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
