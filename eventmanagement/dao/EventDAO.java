package com.acharya.eventmanagement.dao;

import com.acharya.eventmanagement.config.DBConnection;
import com.acharya.eventmanagement.model.Event;
import java.sql.*;

public class EventDAO {

    public int createEvent(Event event) {

        int eventId = 0;
        String sql = "INSERT INTO events(name, objective, estimated_budget) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, event.getName());
            ps.setString(2, event.getObjective());
            ps.setDouble(3, event.getBudget());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                eventId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventId;
    }
}