package com.example.demo.model;

import java.time.LocalDate;

public class DatingUser extends SuperUser {

    private int ID;
    private String name;
    private String description;
    private LocalDate birthdate;
    private int phoneNumber;
    private String sex;
    private String mail;


    public DatingUser(int ID, String name, String description, LocalDate birthdate, int phoneNumber, String sex,
                      String userName, String password) {
        super(userName, password);
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public String getMail(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
