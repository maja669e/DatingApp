package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;
import org.apache.tomcat.jni.Local;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            //ps.setString(4, datingUser.getGender());

            String SQL2 = "INSERT INTO users (name, birthdate) VALUES (?, ?)";
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
            String SQL = "SELECT * FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                String name = rs.getString("name");
                int picture = rs.getInt("picture");
                String description = rs.getString("description");

                String temp = rs.getString("birthdate");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(temp, formatter);

                String gender = rs.getString("gender");
                int id = rs.getInt("userid");
                DatingUser datingUser = new DatingUser(name, email, password, birthDate, role, description, gender);
                datingUser.setID(id);
                datingUser.setPictureid(id);

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
            PreparedStatement ps = con.prepareStatement(SQL);
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

    public ArrayList<DatingUser> getAllDatingUsers() {
        ArrayList<DatingUser> datingUsers = new ArrayList<>();
        DatingUser datingUser;

        try {
            Connection con = DBManager.getConnection();

            String SQL = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            // Get data from database.
            while (rs.next()) {

                int ID = rs.getInt("userid");
                String email = rs.getString("email");
                String role = rs.getString("role");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String description = rs.getString("description");
                String gender = rs.getString("gender");
                int picture = rs.getInt("picture");

               /* String temp = rs.getString("birthdate");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(temp, formatter);*/



                if (role.equals("datinguser")) {
                    datingUser = new DatingUser(ID, name, email, password, role, description, picture, gender);
                    datingUser.setPictureid(ID);
                    datingUsers.add(datingUser);
                }
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return datingUsers;
    }

    public DatingUser removeDatingUser(DatingUser datingUser, String name) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE from users where userid = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, datingUser.getID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return datingUser;
    }


    public DatingUser editDatingUser() {
        return null;
    }

    public void sendMessage(String message, DatingUser datingUser) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO message (message) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, datingUser.setMessage(message + "/from: " + datingUser));
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }
}


