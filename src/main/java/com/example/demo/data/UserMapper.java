package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;

import java.sql.*;
import java.util.ArrayList;

public class UserMapper {

    public void createDatingUser(DatingUser datingUser) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, datingUser.getEmail());
            ps.setString(2, datingUser.getPassword());
            ps.setString(3, datingUser.setRole());
            ps.executeUpdate();

            //ps.setString(5, datingUser.getDescription());
            // ps.setString(4, datingUser.getGender());

            String SQL2 = "INSERT INTO datingusers (name, birthdate) VALUES (?, ?)";
            PreparedStatement ps1 = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, datingUser.getName());
            ps1.setString(2, String.valueOf(datingUser.getBirthdate()));
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
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT userid, role FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("userid");
                DatingUser datingUser = new DatingUser(email, password, role);
                datingUser.setID(id);
                return datingUser;
            } else {
                throw new LoginException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    public AdminUser adminLogin(String email, String password) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT userid, role FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("userid");
                AdminUser adminUser = new AdminUser(email, password, role);
                adminUser.setID(id);
                return adminUser;
            } else {
                throw new LoginException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    public DatingUser getAllUsers() {

        ArrayList<DatingUser> datingUsers = new ArrayList<>();
        DatingUser datingUser = new DatingUser();
        try {
            Connection con = DBManager.getConnection();
            // Prepare SQL.
            String SQL = "SELECT userid, /*name,*/ email FROM datingusers";

            // Set prepared statement.
            PreparedStatement ps = con.prepareStatement(SQL);

            // Execute SQL.
            ResultSet rs = ps.executeQuery();

            // Get data from database.

            while (rs.next()) {

                datingUser.setID(rs.getInt("userid"));
                //datingUser.setName(rs.getString("name"));
                datingUser.setEmail(rs.getString("email"));

                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                datingUser.setID(id);

                datingUsers.add(datingUser);

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return datingUser;
    }
    public ArrayList<DatingUser> getAllUsers2() {

        ArrayList<DatingUser> datingUsers = new ArrayList<>();
        DatingUser datingUser = new DatingUser();
        try {
            Connection con = DBManager.getConnection();
            // Prepare SQL.
            String SQL = "SELECT userid, /*name,*/ email FROM datingusers";

            // Set prepared statement.
            PreparedStatement ps = con.prepareStatement(SQL);

            // Execute SQL.
            ResultSet rs = ps.executeQuery();

            // Get data from database.

            while (rs.next()) {

                datingUser.setID(rs.getInt("userid"));
                //datingUser.setName(rs.getString("name"));
                datingUser.setEmail(rs.getString("email"));

                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                datingUser.setID(id);

                datingUsers.add(datingUser);

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return datingUsers;
    }

}


