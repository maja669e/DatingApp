package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DatingUser extends SuperUser {
    private String name;
    private String description;
    private LocalDate birthdate;
    private String gender;
    private String message;
    private int pictureid;
    private int age;

    public DatingUser(String name, String email, String password, LocalDate birthdate, String role, String description, String gender, int pictureid) {
        super(email, password, role);
        this.birthdate = birthdate;
        this.pictureid = pictureid;
        this.name = name;
        this.description = description;
        this.gender = gender;
    }

    public DatingUser(int ID, String name, String email, String password, String role, String description, int picture, String gender) {
        super(email, password, role);
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.pictureid = picture;
        this.gender = gender;

    }

    public int getPictureid() {
        return pictureid;
    }

    public void setPictureid(int pictureid) {
        this.pictureid = pictureid;
    }

    public String getMessage() {
        return message;
    }

    public String setMessage(String message) {
        this.message = message;
        return message;
    }

    public int getAge() {
        age = Period.between(birthdate, LocalDate.now()).getYears();
        return age;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public String setRole() {
        return "datinguser";
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

}
