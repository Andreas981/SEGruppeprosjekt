package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import Model.Room;
import Model.SeatedPlannedEvent;
import View.OrganizerMenuView;
import org.joda.time.LocalDateTime;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenuController {

    private OrganizerMenuView organizerMenuView = new OrganizerMenuView();


    private void roomMenu(int location, int room) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        try{
            choice = scanner.nextInt();
            switch (choice){
                case 0:
                    seeEventPrompt(location, room);
                    break;
                case 1:
                    addEventPrompt(location, room);
                    break;
                case 2:
                    removeEventPrompt(location, room);
                    break;
                case 3:
                    break;
                case 4:
                    seeSpecificLocation(location);
                    break;
                default:
                    organizerMenuView.displayPromptForNotAnOption();
                    spesificRoom(location, room);
            }
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void seeEventPrompt(int location, int room) {
        organizerMenuView.displayPromptForSeeEvent();
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
            seeEvent(location, room, choice);
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void seeEvent(int location, int room, int event) {
        organizerMenuView.displayPromptForSeeEventDetail(location, room, event);
        spesificRoom(location, room);
    }

    private void removeEventPrompt(int location, int room) {
        organizerMenuView.displayPromptForWhichEventToRemove();
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
            removeEvent(location, room, choice);
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void removeEvent(int location, int room, int event) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().remove(event);
        organizerMenuView.displayPromptForEventRemoved();
        spesificRoom(location, room);
    }

    private void addEventPrompt(int location, int room) {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        String patternForTime = "[0-9][0-9]-[0-9][0-9]";
        organizerMenuView.displayPromptForAddEventName();
        String name = new Scanner(System.in).nextLine();
        organizerMenuView.displayPromptForAddEventDate();
        String date = new Scanner(System.in).next();
        organizerMenuView.displayPromptForAddEventTime();
        String time = new Scanner(System.in).next();
        int ageLimit = -1;
        int lengthOfEvent = -1;
        try{
            organizerMenuView.displayPromptForAddEventAgeLimit();
            ageLimit = new Scanner(System.in).nextInt();

        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }

        if (Security.RegEx.regEx(patternForDate, date) && Security.RegEx.regEx(patternForTime, time) && ageLimit != -1)
            addEvent(location, room, name, date, time, ageLimit, lengthOfEvent);
        else{
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }

    }

    private void addEvent(int location, int room, String name, String date, String time, int ageLimit, int lengthOfEvent) {
        String[] dateArray = date.split("-");
        String[] timeArray = time.split("-");
        Database.currentLoggedInOrganizer
                .getLocations()
                .get(location)
                .getRooms()
                .get(room)
                .getEvents()
                .add(new SeatedPlannedEvent(name, new LocalDateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1])), lengthOfEvent, ageLimit));

        organizerMenuView.displayPromptForAddEventAdded();
        spesificRoom(location, room);
    }


    private void levelOneOrganizer() {
        //TODO Menu for level one organizer
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(0) Add an event");
        System.out.println("\t(1) Sign out");

        int choice;
        try {
            choice = scanner.nextInt();

            switch (choice){
                case 0:
                    addNonSeatedEvent();
                    break;
                case 1:
                    Database.currentLoggedInOrganizer = null;
                    break;
                default:
                    organizerMenuView.displayPromptForNotAnOption();
                    levelOneOrganizer();
                    break;
            }

        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            levelOneOrganizer();
        }

    }

    private void addNonSeatedEvent() {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        organizerMenuView.displayPromptForAddEventName();
        String name = new Scanner(System.in).nextLine();
        organizerMenuView.displayPromptForAddEventDate();
        String date = new Scanner(System.in).next();
        int length = -1;
        int ageLimit = -1;
        int freeSpace = -1;
        try{
            organizerMenuView.displayPromptForAddEventLength();
            organizerMenuView.displayPromptForAddEventLength();
            length = new Scanner(System.in).nextInt();
            organizerMenuView.displayPromptForAddEventAgeLimit();
            ageLimit = new Scanner(System.in).nextInt();
            System.out.println("Space availible: ");
            freeSpace = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            levelOneOrganizer();
        }
        System.out.println("Is the event free? yes/no");
        String freeEvent = new Scanner(System.in).next();
        System.out.println("Meet up: ");
        String meetUp = new Scanner(System.in).next();

        if (name.length() > 0 && Security.RegEx.regEx(patternForDate, date) && length != -1 && ageLimit != -1 && freeSpace != -1 && (freeEvent.split("")[0].toLowerCase().equals("n")||freeEvent.split("")[0].toLowerCase().equals("y")) && meetUp.length() > 0){
            String[] dateArray = date.split("-");
            Database.currentLoggedInOrganizer.addNonSeatedPlannedEvent(new NonSeatedPlannedEvent(name, new LocalDateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), 00, 00), length, ageLimit, freeSpace, meetUp, (freeEvent.split("")[0].toLowerCase().equals("y"))));
            organizerMenuView.displayPromptForAddEventAdded();
            levelOneOrganizer();
        }


    }

}
