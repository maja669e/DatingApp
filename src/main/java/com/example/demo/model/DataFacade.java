package com.example.demo.model;

public interface DataFacade {

    public DatingUser datingLogin(String email, String password) throws LoginSampleException;
    public DatingUser createDatingUser(DatingUser datingUser) throws LoginSampleException;
    public AdminUser adminLogin(String email, String password) throws LoginSampleException;
}


