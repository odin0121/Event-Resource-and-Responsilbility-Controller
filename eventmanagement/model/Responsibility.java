package com.acharya.eventmanagement.model;

import java.time.LocalDate;

public class Responsibility {

    private int eventId;
    private String memberName;
    private String task;
    private LocalDate deadline;

    public Responsibility(int eventId, String memberName,
                          String task, LocalDate deadline) {
        this.eventId = eventId;
        this.memberName = memberName;
        this.task = task;
        this.deadline = deadline;
    }

    public int getEventId() { return eventId; }
    public String getMemberName() { return memberName; }
    public String getTask() { return task; }
    public LocalDate getDeadline() { return deadline; }
}