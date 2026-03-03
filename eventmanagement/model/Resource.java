package com.acharya.eventmanagement.model;

public class Resource {

    private int eventId;
    private String name;
    private String type;

    public Resource(int eventId, String name, String type) {
        this.eventId = eventId;
        this.name = name;
        this.type = type;
    }

    public int getEventId() { return eventId; }
    public String getName() { return name; }
    public String getType() { return type; }
}