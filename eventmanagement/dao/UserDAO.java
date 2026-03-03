package com.acharya.eventmanagement.dao;

import com.acharya.eventmanagement.config.DBConnection;
import com.acharya.eventmanagement.model.User;

import java.sql.*;

public class UserDAO {

    // Register User
    public void register(User user) {

        String sql = "INSERT INTO users(username, password, role) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());

            ps.executeUpdate();
            System.out.println("Registration Successful!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Login Validation
    public boolean login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}