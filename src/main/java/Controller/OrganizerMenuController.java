package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import org.joda.time.LocalDateTime;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenuController {



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
                    //organizerMenuView.displayPromptForNotAnOption();
                    levelOneOrganizer();
                    break;
            }

        }catch (InputMismatchException e){
            //organizerMenuView.displayPromptForNotAnOption();
            levelOneOrganizer();
        }

    }

    private void addNonSeatedEvent() {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        //organizerMenuView.displayPromptForAddEventName();
        String name = new Scanner(System.in).nextLine();
        //organizerMenuView.displayPromptForAddEventDate();
        String date = new Scanner(System.in).next();
        int length = -1;
        int ageLimit = -1;
        int freeSpace = -1;
        try{
            //organizerMenuView.displayPromptForAddEventLength();
            //organizerMenuView.displayPromptForAddEventLength();
            length = new Scanner(System.in).nextInt();
            //organizerMenuView.displayPromptForAddEventAgeLimit();
            ageLimit = new Scanner(System.in).nextInt();
            System.out.println("Space availible: ");
            freeSpace = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
           // organizerMenuView.displayPromptForNotAnOption();
            levelOneOrganizer();
        }
        System.out.println("Is the event free? yes/no");
        String freeEvent = new Scanner(System.in).next();
        System.out.println("Meet up: ");
        String meetUp = new Scanner(System.in).next();

        if (name.length() > 0 && Security.RegEx.regEx(patternForDate, date) && length != -1 && ageLimit != -1 && freeSpace != -1 && (freeEvent.split("")[0].toLowerCase().equals("n")||freeEvent.split("")[0].toLowerCase().equals("y")) && meetUp.length() > 0){
            String[] dateArray = date.split("-");
            Database.currentLoggedInOrganizer.addNonSeatedPlannedEvent(new NonSeatedPlannedEvent(name, new LocalDateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), 00, 00), length, ageLimit, freeSpace, meetUp, (freeEvent.split("")[0].toLowerCase().equals("y"))));
           // organizerMenuView.displayPromptForAddEventAdded();
            levelOneOrganizer();
        }


    }

}
