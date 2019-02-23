package View;

import Dummy.Database;
import Model.Location;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nName of location?");
        String name = scanner.next();
        System.out.println("Location address?");
        String address = scanner.next();
        System.out.println("Is it a public location? (1)yes (2)no");
        int publicLocaiton = scanner.nextInt();

        if(Controller.Location.addLocation(name, address, publicLocaiton)){
            System.out.println("Location added");
            seeLocation();
        }else{
            System.out.println("Something went wrong");
            levelTwoOrganizer();
        }

    }

    private static void seeLocation() {

        System.out.println("\nThis is you're registered locations: ");
        for (int i = 0; i < Database.currentLoggedInOrganizer.getLocations().size(); i++){
            System.out.println("(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName());
        }

        locationMenu();

    }

    private static void locationMenu() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you do?");
        System.out.println("(1) See a location");
        System.out.println("(2) Add a location");
        System.out.println("(3) Go back");

        int choice = 0;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
            locationMenu();
        }

        switch (choice){
            case 1:
                break;
            case 2:
                //Add location
                addLocation();
                break;
            case 3:
                break;
            default:
                System.out.println("Sorry, that is not an option");
                break;
        }

    }

    private static void levelOneOrganizer() {

    }
}
