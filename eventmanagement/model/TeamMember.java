package com.acharya.eventmanagement.model;

public class TeamMember {

    private int eventId;
    private String name;
    private String role;

    public TeamMember(int eventId, String name, String role) {
        this.eventId = eventId;
        this.name = name;
        this.role = role;
    }

    public int getEventId() { return eventId; }
    public String getName() { return name; }
    public String getRole() { return role; }
}