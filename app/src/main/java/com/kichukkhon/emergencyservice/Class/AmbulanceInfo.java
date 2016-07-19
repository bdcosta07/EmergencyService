package com.kichukkhon.emergencyservice.Class;

/**
 * Created by Bridget on 7/14/2016.
 */
public class AmbulanceInfo {
    private int id;
    private int areaId;
    private String orgName;
    private String address;
    private String phoneNo;

    public AmbulanceInfo(int id, int areaId, String orgName, String address, String phoneNo) {
        this.id = id;
        this.areaId = areaId;
        this.orgName = orgName;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public AmbulanceInfo() {

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
