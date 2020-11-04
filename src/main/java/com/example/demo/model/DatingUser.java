package com.example.demo.model;

import java.util.Date;

public class DatingUser extends BasicUser {

    protected String name;
    protected String description;
    protected Date birthdate;
    protected int phoneNumber;
    protected String sex;
    protected String orientation;


    public DatingUser(String name, String description, Date birthdate, int phoneNumber, String sex, String orientation,
                      String userName, String password) {
        super(userName, password);
        this.name = name;
        this.description = description;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.orientation = orientation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
