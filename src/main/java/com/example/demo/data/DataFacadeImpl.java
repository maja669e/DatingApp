package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DataFacade;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;

public class DataFacadeImpl implements DataFacade {
    private UserMapper userMapper = new UserMapper();

    @Override
    public DatingUser datingLogin(String email, String password) throws LoginException {
        return null;
    }

    @Override
    public DatingUser createDatingUser(DatingUser datingUser) throws LoginException {
        return null;
    }

    @Override
    public AdminUser adminLogin(String email, String password) throws LoginException {
        return null;
    }
}
