package com.acharya.eventmanagement.dao;

import com.acharya.eventmanagement.config.DBConnection;
import com.acharya.eventmanagement.model.Resource;
import java.sql.*;

public class ResourceDAO {
	public void viewResourcesWithEvent() {

	    String sql = "SELECT e.name AS event_name, r.name AS resource_name, r.type, r.allocated " +
	                 "FROM resources r " +
	                 "JOIN events e ON r.event_id = e.id";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n--- Resources with Event Name ---");

	        while (rs.next()) {
	            System.out.println(
	                    "Event: " + rs.getString("event_name") +
	                    " | Resource: " + rs.getString("resource_name") +
	                    " | Type: " + rs.getString("type") +
	                    " | Allocated: " + rs.getBoolean("allocated")
	            );
	        }

	        System.out.println();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    public void addResource(Resource resource) {

        String sql = "INSERT INTO resources(event_id, name, type, allocated) VALUES (?, ?, ?, false)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, resource.getEventId());
            ps.setString(2, resource.getName());
            ps.setString(3, resource.getType());

            ps.executeUpdate();
            System.out.println("Resource Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
