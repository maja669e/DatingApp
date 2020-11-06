package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;

import java.sql.*;

public class UserMapper {

    public void createDatingUser(DatingUser datingUser) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO users (name, email, password) VALUES ( ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, datingUser.getName());
            ps.setString(2, datingUser.getEmail());
            ps.setString(3, datingUser.getPassword());
            ps.executeUpdate();

            // ps.setString(4, datingUser.getRole());
            //ps.setString(5, datingUser.getDescription());
            // ps.setString(4, datingUser.getGender());

            String SQL2 = "INSERT INTO datingusers (birthdate) VALUES (?)";
            PreparedStatement ps1 = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, String.valueOf(datingUser.getBirthdate()));
            ps1.executeUpdate();

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
