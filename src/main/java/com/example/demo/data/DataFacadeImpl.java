package com.example.demo.data;

import com.example.demo.model.*;

import java.util.ArrayList;
import java.util.List;


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

    public List<DatingUser> getAllDatingUsers(SuperUser loginUser) {
        return userMapper.getAllDatingUsers(loginUser);
    }

    public void editUserInfo(DatingUser datingUser, String name, String email, String password, String gender, String description){
        userMapper.editUserInfo(datingUser, name, email, password, gender, description);
    }

    public void deleteUser(int userid){
        adminMapper.deleteUser(userid);
    }

    public DatingUser updateDatingUser(int userid){
        return userMapper.updateDatingUser(userid);
    }

    public void sendMessage(String message, int senderid, int receiveid){
        userMapper.sendMessage(message,senderid,receiveid);
    }
}
