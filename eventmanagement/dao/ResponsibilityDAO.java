package com.acharya.eventmanagement.dao;

import com.acharya.eventmanagement.config.DBConnection;
import com.acharya.eventmanagement.model.*;

import java.sql.*;

public class ResponsibilityDAO {

    public void addTeamMember(TeamMember m) {

        String sql = "INSERT INTO team_members(event_id, name, role) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getEventId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getRole());

            ps.executeUpdate();
            System.out.println("Team Member Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewResponsibilitiesWithEvent() {

        String sql = "SELECT e.name AS event_name, r.member_name, r.task, r.deadline, r.status " +
                     "FROM responsibilities r " +
                     "JOIN events e ON r.event_id = e.id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Responsibilities with Event Name ---");

            while (rs.next()) {
                System.out.println(
                        "Event: " + rs.getString("event_name") +
                        " | Member: " + rs.getString("member_name") +
                        " | Task: " + rs.getString("task") +
                        " | Deadline: " + rs.getDate("deadline") +
                        " | Status: " + rs.getString("status")
                );
            }

            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void assignResponsibility(Responsibility r) {

        String sql = "INSERT INTO responsibilities(event_id, member_name, task, deadline, status) VALUES (?, ?, ?, ?, 'Pending')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getEventId());
            ps.setString(2, r.getMemberName());
            ps.setString(3, r.getTask());
            ps.setDate(4, Date.valueOf(r.getDeadline()));

            ps.executeUpdate();
            System.out.println("Responsibility Assigned!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewResponsibilities(int eventId) {

        String sql = "SELECT * FROM responsibilities WHERE event_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Responsibilities ---");

            while (rs.next()) {
                System.out.println(
                        "Member: " + rs.getString("member_name") +
                        " | Task: " + rs.getString("task") +
                        " | Deadline: " + rs.getDate("deadline") +
                        " | Status: " + rs.getString("status"));
            }

            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void markCompleted(int eventId, String task) {

        String sql = "UPDATE responsibilities SET status='Completed' WHERE event_id=? AND task=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, eventId);
            ps.setString(2, task);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Task Completed!");
            else
                System.out.println("Task Not Found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteResponsibility(int eventId, String task) {

        String sql = "DELETE FROM responsibilities WHERE event_id=? AND task=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, eventId);
            ps.setString(2, task);

            ps.executeUpdate();
            System.out.println("Deleted Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewPending(int eventId) {

        String sql = "SELECT * FROM responsibilities WHERE event_id=? AND status='Pending'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Pending Tasks ---");

            while (rs.next()) {
                System.out.println(
                        "Member: " + rs.getString("member_name") +
                        " | Task: " + rs.getString("task") +
                        " | Deadline: " + rs.getDate("deadline"));
            }

            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}