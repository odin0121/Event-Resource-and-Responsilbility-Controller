package com.acharya.eventmanagement.dao;

import com.acharya.eventmanagement.config.DBConnection;
import com.acharya.eventmanagement.model.Expense;
import java.sql.*;

public class ExpenseDAO {

    public void addExpense(Expense expense) {

        String sql = "INSERT INTO expenses(event_id, amount, description) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, expense.getEventId());
            ps.setDouble(2, expense.getAmount());
            ps.setString(3, expense.getDescription());

            ps.executeUpdate();
            System.out.println("Expense Recorded!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}