package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.DatingUser;
import com.example.demo.model.LoginException;
import com.example.demo.model.SuperUser;
import org.apache.tomcat.jni.Local;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatingUserMapper {

    public void createDatingUser(DatingUser datingUser) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO users (email, password, role, name, birthdate,picture) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, datingUser.getEmail());
            ps.setString(2, datingUser.getPassword());
            ps.setString(3, datingUser.setRole());
            ps.setString(4, datingUser.getName());
            ps.setString(5, String.valueOf(datingUser.getBirthdate()));
            ps.setString(6, String.valueOf(0));
            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            datingUser.setID(id);

        } catch (SQLException ex) {
            throw new LoginException("Email eksisterer allerede - prøv igen");
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
                int pictureid = rs.getInt("picture");
                String description = rs.getString("description");

                String temp = rs.getString("birthdate");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(temp, formatter);

                String gender = rs.getString("gender");
                int id = rs.getInt("userid");
                DatingUser datingUser = new DatingUser(name, email, password, birthDate, role, description, gender, pictureid);
                datingUser.setID(id);
                datingUser.setPictureid(id);

                return datingUser;

            } else {
                throw new LoginException("Kan ikke valider bruger - prøv igen");
            }
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    public void editUserInfo(DatingUser datingUser, String name, String email, String password, String gender, String description){
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE users set email = ?, name = ?, gender = ?, description= ?, password= ? WHERE userid = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, description);
            ps.setString(5, password);
            int userid = datingUser.getID();
            ps.setInt(6, userid);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Kunne ikke redigere brugeren");
        }
    }

    public List<DatingUser> getAllDatingUsers(SuperUser loginUser) {
        List<DatingUser> datingUsers = new ArrayList<>();
        DatingUser datingUser;

        try {
            Connection con = DBManager.getConnection();

            String SQL = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            // Get data from database.
            while (rs.next()) {
                String role = rs.getString("role");
                if (role.equals("datinguser")) {
                    int ID = rs.getInt("userid");
                    String temp = rs.getString("birthdate");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate birthDate = LocalDate.parse(temp, formatter);
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String description = rs.getString("description");
                    String gender = rs.getString("gender");
                    int picture = rs.getInt("picture");
                    datingUser = new DatingUser(name, email, password, birthDate, role, description, gender, picture);
                    datingUser.setID(ID);
                    if (picture != 0) {
                        datingUser.setPictureid(ID);
                    }

                    if (!(loginUser.getID() == datingUser.getID())) {
                        datingUsers.add(datingUser);
                    }
                }
            }


        } catch (SQLException ex) {
            System.out.println("Kunne ikke finde brugere");
        }
        return datingUsers;
    }

    public DatingUser updateDatingUser(int userid) {
        DatingUser updated = null;

        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM users " + "WHERE userid= ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                int pictureid = rs.getInt("picture");
                String password = rs.getString("password");
                int id = rs.getInt("userid");

                String temp = rs.getString("birthdate");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(temp, formatter);

                String email = rs.getString("email");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String gender = rs.getString("gender");

                updated = new DatingUser(name, email, password, birthDate, role, description, gender, pictureid);
                updated.setID(id);

                if (pictureid != 0) {
                    updated.setPictureid(id);
                } else {
                    updated.setPictureid(0);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Kunne ikke opdater bruger");
        }
        return updated;
    }

    public void sendMessage(String message, int senderid, int receiveid) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO messages (senderid, receiveid, message) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, senderid);
            ps.setInt(2, receiveid);
            ps.setString(3, message);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Kunne ikke sende besked");
        }
    }

}


