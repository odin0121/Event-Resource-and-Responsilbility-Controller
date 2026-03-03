package com.acharya.eventmanagement.controller;

import com.acharya.eventmanagement.dao.*;
import com.acharya.eventmanagement.model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class EventController {

    static Scanner sc = new Scanner(System.in);
    static int eventId = 0;

    static EventDAO eventDAO = new EventDAO();
    static ResourceDAO resourceDAO = new ResourceDAO();
    static ResponsibilityDAO responsibilityDAO = new ResponsibilityDAO();
    static ExpenseDAO expenseDAO = new ExpenseDAO();
    static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
    

    	    int choice;

    	    // LOGIN / REGISTER MENU
    	    System.out.println("==== EVENT MANAGEMENT SYSTEM ====");
    	    System.out.println("1. Register");
    	    System.out.println("2. Login");
    	    System.out.print("Choice: ");

    	    choice = sc.nextInt();
    	    sc.nextLine();

    	    if (choice == 1) {

    	        System.out.print("Username: ");
    	        String username = sc.nextLine();

    	        System.out.print("Password: ");
    	        String password = sc.nextLine();

    	        System.out.print("Role (Admin/User): ");
    	        String role = sc.nextLine();

    	        userDAO.register(new User(username, password, role));

    	        System.out.println("Please login to continue.");
    	    }

    	    // LOGIN LOOP
    	    boolean loggedIn = false;

    	    while (!loggedIn) {

    	        System.out.print("Username: ");
    	        String username = sc.nextLine();

    	        System.out.print("Password: ");
    	        String password = sc.nextLine();

    	        if (userDAO.login(username, password)) {
    	            loggedIn = true;
    	            System.out.println("Login Successful!");
    	        } else {
    	            System.out.println("Invalid Credentials! Try Again.");
    	        }
    	    }

    	  
 

        do {
            System.out.println("\n==== EVENT MANAGEMENT SYSTEM ====");
            System.out.println("1. Create Event");
            System.out.println("2. Add Resource");
            System.out.println("3. Add Team Member");
            System.out.println("4. Assign Responsibility");
            System.out.println("5. Add Expense");
            System.out.println("6. View Responsibilities");
            System.out.println("7. Mark Task Completed");
            System.out.println("8. Delete Responsibility");
            System.out.println("9. View Pending Tasks");
            System.out.println("10. View Responsibilities (With Event)");
            System.out.println("11. View Resources (With Event)");
            System.out.println("12. Exit");
            System.out.print("Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Objective: ");
                    String obj = sc.nextLine();
                    System.out.print("Budget: ");
                    double bud = sc.nextDouble();
                    sc.nextLine();
                    eventId = eventDAO.createEvent(new Event(name, obj, bud));
                    System.out.println("Event Created! ID: " + eventId);
                    break;

                case 2:
                    checkEvent();
                    System.out.print("Resource Name: ");
                    String rname = sc.nextLine();
                    System.out.print("Resource Type: ");
                    String rtype = sc.nextLine();
                    resourceDAO.addResource(
                            new Resource(eventId, rname, rtype));
                    break;

                case 3:
                    checkEvent();
                    System.out.print("Member Name: ");
                    String m = sc.nextLine();
                    System.out.print("Role: ");
                    String r = sc.nextLine();
                    responsibilityDAO.addTeamMember(new TeamMember(eventId, m, r));
                    break;

                case 4:
                    checkEvent();
                    System.out.print("Member Name: ");
                    String mem = sc.nextLine();
                    System.out.print("Task: ");
                    String task = sc.nextLine();
                    System.out.print("Deadline (YYYY-MM-DD): ");
                    LocalDate d = LocalDate.parse(sc.nextLine());
                    responsibilityDAO.assignResponsibility(
                            new Responsibility(eventId, mem, task, d));
                    break;

                case 5:
                    checkEvent();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    expenseDAO.addExpense(new Expense(eventId, amt, desc));
                    break;

                case 6:
                    responsibilityDAO.viewResponsibilities(eventId);
                    break;

                case 7:
                    System.out.print("Task Name: ");
                    responsibilityDAO.markCompleted(eventId, sc.nextLine());
                    break;

                case 8:
                    System.out.print("Task Name: ");
                    responsibilityDAO.deleteResponsibility(eventId, sc.nextLine());
                    break;

                case 9:
                    responsibilityDAO.viewPending(eventId);
                    break;
                case 10:
                    responsibilityDAO.viewResponsibilitiesWithEvent();
                    break;
                case 11:
                    resourceDAO.viewResourcesWithEvent();
                    break;
                case 12:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        }  while (choice != 12);
    }

    private static void checkEvent() {
        if (eventId == 0) {
            System.out.println("Please Create Event First!");
            System.exit(0);
        }
    }
}