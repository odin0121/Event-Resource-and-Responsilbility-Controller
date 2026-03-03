package com.acharya.eventmanagement.model;

public class Expense {

    private int eventId;
    private double amount;
    private String description;

    public Expense(int eventId, double amount, String description) {
        this.eventId = eventId;
        this.amount = amount;
        this.description = description;
    }

    public int getEventId() { return eventId; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
}