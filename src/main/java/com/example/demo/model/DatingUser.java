package com.example.demo.model;

import java.time.LocalDate;

public class DatingUser extends SuperUser {
    private int ID;
    private String name;
    private String description;
    private LocalDate birthdate;
    private String sex;


    public DatingUser(String name, /*LocalDate birthdate,*/
                      String email, String password) {
        super(email, password);
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.birthdate = birthdate;
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

    public String getSex() {
        return sex;
    }

    public String getEmail(){
        return email;
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

    public void setSex(String sex) {
        this.sex = sex;
    }

}
