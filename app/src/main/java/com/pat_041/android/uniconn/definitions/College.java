package com.pat_041.android.uniconn.definitions;

import android.content.Context;

import java.util.ArrayList;

/**
 * Main Object for  The returned University
 */

public class College extends SuperObjects {

    public int id;

    public int ranking;
    //1
    public String collegeName;
    //1
    public String address;
    //1
    public String city;
    //1
    public String state;
    //1
    public String est_date;
    //1
    public String district;
    //1
    public String pincode;
    //1
    public String website;
    //Not Listed
    public String area;
    //Not Listed
    public String affiliate;
    //In Map
    public String latitute;
    //In Map
    public String longitude;
    //Not Listed
    public String management;
    //3 - Need More Info On Hostels
    public boolean hostelAvailable;
    //2
    public boolean scholarshipAvailable;
    //2
    public boolean fellowShipAvailable;
    //2
    public ArrayList<String> dept;
    //2
    public ArrayList<String> course_level;
    //2
    public ArrayList<String> Infrastructure;

    public College(int id) {
        this.id = id;
    }

    public College() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
        this.heading = collegeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEst_date() {
        return est_date;
    }

    public void setEst_date(String est_date) {
        this.est_date = est_date;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }

    public String getLatitute() {
        return latitute;
    }

    public void setLatitute(String latitute) {
        this.latitute = latitute;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public boolean isHostelAvailable() {
        return hostelAvailable;
    }

    public void setHostelAvailable(boolean hostelAvailable) {
        this.hostelAvailable = hostelAvailable;
    }

    public boolean isScholarshipAvailable() {
        return scholarshipAvailable;
    }

    public void setScholarshipAvailable(boolean scholarshipAvailable) {
        this.scholarshipAvailable = scholarshipAvailable;
    }

    public boolean isFellowShipAvailable() {
        return fellowShipAvailable;
    }

    public void setFellowShipAvailable(boolean fellowShipAvailable) {
        this.fellowShipAvailable = fellowShipAvailable;
    }

    public ArrayList<String> getDept() {
        return dept;
    }

    public void setDept(ArrayList<String> dept) {
        this.dept = dept;
    }

    public ArrayList<String> getCourse_level() {
        return course_level;
    }

    public void setCourse_level(ArrayList<String> course_level) {
        this.course_level = course_level;
    }

    public ArrayList<String> getInfrastructure() {
        return Infrastructure;
    }

    public void setInfrastructure(ArrayList<String> infrastructure) {
        Infrastructure = infrastructure;
    }

    public void setExtra(String city, String state){
        this.extra = city+". "+state;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
