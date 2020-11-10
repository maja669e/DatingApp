package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DataFacade;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;

import java.util.ArrayList;


public class DataFacadeImpl implements DataFacade {
    private DatingUserMapper userMapper = new DatingUserMapper();
    private AdminUserMapper adminMapper =new AdminUserMapper();

    public DatingUser datingLogin(String email, String password) throws LoginException {
        return userMapper.datingLogin(email, password);
    }

    public DatingUser createDatingUser(DatingUser datingUser) throws LoginException {
        userMapper.createDatingUser(datingUser);
        return datingUser;
    }


    public AdminUser adminLogin(String email, String password) throws LoginException {
        return  adminMapper.adminLogin(email, password);
    }

    public ArrayList<DatingUser> getAllDatingUsers() {
        return userMapper.getAllDatingUsers();
    }

    public void editUser(DatingUser datingUser, String name, String email, String gender, String description) throws LoginException {
        userMapper.editUser(datingUser, name,email,gender,description);
    }
}
