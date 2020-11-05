package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;

import java.sql.*;

public class UserMapper {

    public void createDatingUser(DatingUser datingUser) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL ="";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet ids = ps.getGeneratedKeys();
            ps.executeUpdate();
            ids.next();
            int id = ids.getInt(1);
            //datingUser.setId(id);
        }catch (SQLException sqlerr){
            throw new LoginException(sqlerr.getMessage());
        }
    }

    public DatingUser datingLogin(String email, String password) throws LoginException{
        return null;
    }

    public AdminUser adminLogin(String email, String password) throws LoginException{
        return null;
    }
}
