package com.example.demo.data;

import com.example.demo.DemoApplication;
import com.example.demo.model.AdminUser;
import com.example.demo.model.DataFacade;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginSampleException;


public class DataFacadeImpl implements DataFacade {
    private UserMapper userMapper = new UserMapper();

    public DatingUser datingLogin(String email, String password) throws LoginSampleException {
        return userMapper.datingLogin(email, password);
    }

    public DatingUser createDatingUser(DatingUser datingUser) throws LoginSampleException {
        userMapper.createDatingUser(datingUser);
        return datingUser;
    }

    @Override
    public AdminUser adminLogin(String email, String password) throws LoginSampleException {
        return null;
    }
}
