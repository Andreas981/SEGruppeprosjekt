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

        System.out.println("\nWhat would you do?");
        System.out.println("(1) See a location");
        System.out.println("(2) Add a location");
        System.out.println("(3) Remove a location");
        System.out.println("(4) Go back");

        //TODO Add Remove a location functionality

        int choice = 0;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
            locationMenu();
        }

        switch (choice){
            case 1:
                //Se specific locaiton
                System.out.println("\nWhich location do you want to see?");
                int location;
                try{
                    location = scanner.nextInt();
                    seeSpecificLocation(location);
                }catch (InputMismatchException e){
                    System.out.println("Sorry, that is not an option");
                    locationMenu();
                }

                break;
            case 2:
                //Add location
                addLocation();
                break;
            case 3:
                //Remove location
                break;
            case 4:
                levelTwoOrganizer();
                break;

            default:
                System.out.println("Sorry, that is not an option");
                break;
        }

    }

    private static void seeSpecificLocation(int location) {
        if (location >= 0 && location <= Database.currentLoggedInOrganizer.getLocations().size()){
            System.out.println("\nName: "+Database.currentLoggedInOrganizer.getLocations().get(location).getName());
            System.out.println("Address: "+Database.currentLoggedInOrganizer.getLocations().get(location).getAddress());
            System.out.println("Is public: "+Database.currentLoggedInOrganizer.getLocations().get(location).getPublicLocation());
            if (Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() == 0){
                System.out.println("Thre is no registered rooms for this locaton");
            }else{
                System.out.println("Rooms for this location: ");
                for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size(); i++){
                    System.out.println("\t(" + i + ")" + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(i).getName());
                }
            }


            //TODO Add functionality to Rooms within a location


        }else{
            System.out.println("That is not a option");
            locationMenu();
        }
    }

    private static void levelOneOrganizer() {
        //TODO Menu for level one organizer
    }
}
