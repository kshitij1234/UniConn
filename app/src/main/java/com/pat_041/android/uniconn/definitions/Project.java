package com.pat_041.android.uniconn.definitions;

/**
 * Created by jaita on 14-Oct-17.
 */

public class Project {

    private int id;

    private int cid;

    private String name;

    private String tag;

    private String info;

    private String sdate;

    private String duration;

    private String location;

    public Project(int id) {
        this.id = id;
    }

    public Project(){}

    public Project(int id, int cid, String name, String tag, String info, String sdate, String duration, String location) {
        this.id = id;
        this.cid = cid;
        this.name = name;
        this.tag = tag;
        this.info = info;
        this.sdate = sdate;
        this.duration = duration;
        this.location = location;
    }

    public Project(int cid, String name, String tag, String info, String sdate, String duration, String location) {
        this.cid = cid;
        this.name = name;
        this.tag = tag;
        this.info = info;
        this.sdate = sdate;
        this.duration = duration;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
