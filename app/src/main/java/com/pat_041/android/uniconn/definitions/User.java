package com.pat_041.android.uniconn.definitions;

import java.io.Serializable;

/**
 * Created by jaita on 14-Oct-17.
 */

public class User implements Serializable{

    private int id;

    private String uname;

    private  String name;

    private String password;

    private String address;

    private String institute;

    private String city;

    private String state;

    private String type;

    private String email;

    private String phone;

    public User() {}

    public User(int id) {
        this.id = id;
    }

    public User(String uname, String password) {
        this.uname = uname;
        this.password = password;
    }

    public User(int id, String uname, String name, String password, String address, String institute, String city, String state, String type, String email, String phone) {
        this.id = id;
        this.uname = uname;
        this.name = name;
        this.password = password;
        this.address = address;
        this.institute = institute;
        this.city = city;
        this.state = state;
        this.type = type;
        this.email = email;
        this.phone = phone;
    }

    public User(String uname, String name, String password, String address, String institute, String city, String state, String type, String email, String phone) {
        this.uname = uname;
        this.name = name;
        this.password = password;
        this.address = address;
        this.institute = institute;
        this.city = city;
        this.state = state;
        this.type = type;
        this.email = email;
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
