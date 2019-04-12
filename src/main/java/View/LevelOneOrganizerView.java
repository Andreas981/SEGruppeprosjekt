package View;

import Controller.NonSeatedEventController;
import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LevelOneOrganizerView {

    private NonSeatedEventController eventController = new NonSeatedEventController();

    public void startView() {

        System.out.println("\nThis is your registred events: ");
        if(Database.currentLoggedInOrganizer.getNonSeatedPlannedEvents().size() != 0)
            for(int i = 0; i < Database.currentLoggedInOrganizer.getNonSeatedPlannedEvents().size(); i++)
                System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName());
        else
            System.out.println("\t**No events have been added**");

        System.out.println("\nWhat would you like to do?");
        System.out.println("\tType '0' Add an event");
        System.out.println("\tType '1' Sign out");

        int choice = -1;
        try {
            choice = new Scanner(System.in).nextInt();

        }catch (InputMismatchException e){
            displayForNotAnOption();
        }
        if(choice != -1)
            switch (choice){
                case 0:
                    addNonSeatedEvent();
                    break;
                case 1:
                    return;
                default:
                    displayForNotAnOption();
                    break;
            }
        startView();
    }

    private void addNonSeatedEvent() {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        String patternForTime = "[0-9][0-9]-[0-9][0-9]";
        System.out.println("\nName of the event:");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Date of event (YYYY-MM-DD): ");
        String date = new Scanner(System.in).next();
        System.out.println("Start time of event (HH-MM): ");
        String time = new Scanner(System.in).next();
        int ageLimit = -1;
        int lengthOfEvent = -1;
        try {
            System.out.println("The events age limit:");
            ageLimit = new Scanner(System.in).nextInt();
            System.out.println("Length of event (in hours):");
            lengthOfEvent = new Scanner(System.in).nextInt();

        } catch (InputMismatchException e) {
            displayForNotAnOption();
            return;
        }
        System.out.println("Price (in nok): ");
        int price = -1;
        try{
            price = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayForNotAnOption();
            return;
        }

        System.out.println("Number of attendees:");
        int freeSpace = -1;
        try{
            freeSpace = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayForNotAnOption();
            return;
        }
        System.out.println("Address of the event:");
        String address = new Scanner(System.in).nextLine();

        eventController.addNonSeatedEvent(name, date, time, lengthOfEvent, ageLimit, freeSpace, address, "f", 100);

        System.out.println("Your event in registered!");
    }

    private void displayForNotAnOption(){
        System.out.println("Sorry, that is not an option");
    }
}
