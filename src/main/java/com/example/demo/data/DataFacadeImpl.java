package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DataFacade;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;

import java.util.ArrayList;


public class DataFacadeImpl implements DataFacade {
    private UserMapper userMapper = new UserMapper();

    public DatingUser datingLogin(String email, String password) throws LoginException {
        return userMapper.datingLogin(email, password);
    }

    public DatingUser createDatingUser(DatingUser datingUser) throws LoginException {
        userMapper.createDatingUser(datingUser);
        return datingUser;
    }


    public AdminUser adminLogin(String email, String password) throws LoginException {
        return userMapper.adminLogin(email, password);
    }

    public ArrayList<DatingUser> getAllDatingUsers() {
        return userMapper.getAllDatingUsers();
    }

}
