package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginSampleException;

import java.sql.*;

public class UserMapper {

    /*public void createDatingUser(DatingUser datingUser) throws LoginSampleException {
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
            throw new LoginSampleException(sqlerr.getMessage());
        }
    }*/

    public void createDatingUser(DatingUser datingUser) throws LoginSampleException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO datingusers (name, email, password) VALUES ( ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, datingUser.getName());
            //ps.setString(2, String.valueOf(datingUser.getBirthdate()));
            ps.setString(2, datingUser.getEmail());
            ps.setString(3, datingUser.getPassword());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            datingUser.setID(id);
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public DatingUser datingLogin(String email, String password) throws LoginSampleException {
        return null;
    }

    public AdminUser adminLogin(String email, String password) throws LoginSampleException{
        return null;
    }
}
