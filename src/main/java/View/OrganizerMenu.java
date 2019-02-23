package View;

import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenu {

    public static void OrganizerMenu(){
        if (Database.currentLoggedInOrganizer.getAccessLevel() == 1){
            levelOneOrganizer();
        }else if(Database.currentLoggedInOrganizer.getAccessLevel() == 2){
            levelTwoOrganizer();
        }
    }

    private static void levelTwoOrganizer() {

        Scanner scanner = new Scanner(System.in);

        //The menu that the organizer will see
        System.out.println("What would you like to do?");
        System.out.println("(1) See locations");
        System.out.println("(2) Add location");

        int choice;
        try{
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    seeLocation();
                    break;
                case 2:
                    addLocation();
                    break;
                default:
                    System.out.println("Sorry, that is not an option");
                    OrganizerMenu.levelTwoOrganizer();
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
            OrganizerMenu.levelTwoOrganizer();
        }

    }

    private static void addLocation() {

    }

    private static void seeLocation() {
        System.out.println("\nThis is you're registered locations: ");
        for (int i = 0; i < Database.currentLoggedInOrganizer.getLocations().size(); i++){
            System.out.println("(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName());
        }
        
    }

    private static void levelOneOrganizer() {

    }
}
