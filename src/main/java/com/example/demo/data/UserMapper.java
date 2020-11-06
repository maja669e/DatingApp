package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;

import java.sql.*;

public class UserMapper {

    public void createDatingUser(DatingUser datingUser) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO datingusers (name, birthdate, email, password) VALUES ( ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, datingUser.getName());
            ps.setString(2, String.valueOf(datingUser.getBirthdate()));
            ps.setString(3, datingUser.getEmail());
            ps.setString(4, datingUser.getPassword());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            datingUser.setID(id);
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    public DatingUser datingLogin(String email, String password) throws LoginException {
        return null;
    }

    public AdminUser adminLogin(String email, String password) throws LoginException {
        return null;
    }
}
