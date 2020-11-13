package com.example.demo.model;

import java.util.Objects;

public abstract class SuperUser {
    protected int ID;

    protected String email;
    protected String password;
    protected String role;

    public SuperUser(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuperUser)) return false;
        SuperUser superUser = (SuperUser) o;
        return ID == superUser.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public abstract int getID();
    public abstract String getRole();
    public abstract String setRole();
    public abstract void setEmail(String email);
}
