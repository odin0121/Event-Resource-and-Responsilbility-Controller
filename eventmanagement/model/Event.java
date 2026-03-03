package com.acharya.eventmanagement.model;

public class Event {

    private String name;
    private String objective;
    private double budget;

    public Event(String name, String objective, double budget) {
        this.name = name;
        this.objective = objective;
        this.budget = budget;
    }

    public String getName() { return name; }
    public String getObjective() { return objective; }
    public double getBudget() { return budget; }
}