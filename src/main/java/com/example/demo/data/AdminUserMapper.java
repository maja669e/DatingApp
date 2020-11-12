package com.example.demo.data;

import com.example.demo.model.AdminUser;
import com.example.demo.model.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserMapper {
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
                throw new LoginException("Kan ikke valider bruger - prøv igen");
            }
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }


    public void deleteUser(int userid){
        try {
            Connection con = DBManager.getConnection();
            //Need to delete this first because of primary key userid
            String SQL = "DELETE FROM messages WHERE senderid = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,userid);
            ps.executeUpdate();

            //Need to delete this first because of primary key userid
            String SQL1 = "DELETE FROM messages WHERE receiveid = ?";
            PreparedStatement ps1 = con.prepareStatement(SQL1);
            ps1.setInt(1,userid);
            ps1.executeUpdate();

            String SQL2 = "DELETE FROM users WHERE userid = ?";
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ps2.setInt(1, userid);
            ps2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Kunne ikke slette brugeren fra admin login");
        }
    }

}
