package View;

import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenu {

    public void promptMenuLevelTwo() {

        //The menu that the organizer will see
        System.out.println("What would you like to do?");
        System.out.println("(1) See locations");
        System.out.println("(2) Add location");


    }


    private static void removeLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which location do you want to remove?");
        int choice;
        try{
            choice = scanner.nextInt();
            System.out.println("Are you sure?");
            System.out.println("yes(y)/no(n)");
            String confirm = scanner.next();
            if (confirm.toLowerCase().startsWith("n")){
                //locationMenu();
            }else if(confirm.toLowerCase().startsWith("y")){
                if(choice >= 0 && choice <= Database.currentLoggedInOrganizer.getLocations().size()){
                    Database.currentLoggedInOrganizer.getLocations().remove(choice);
                    System.out.println("Location deleted!");
                    //seeLocation();
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not a legal choice");
            //seeLocation();
        }
    }

    private static void seeSpecificLocation(int location) {
        if (location >= 0 && location <= Database.currentLoggedInOrganizer.getLocations().size()){
            System.out.println("\nName: "+Database.currentLoggedInOrganizer.getLocations().get(location).getName() +
                    "\nAddress: "+Database.currentLoggedInOrganizer.getLocations().get(location).getAddress() +
                    "\nIs public: "+Database.currentLoggedInOrganizer.getLocations().get(location).getPublicLocation()
            );
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
            //locationMenu();
        }
    }

    private static void levelOneOrganizer() {
        //TODO Menu for level one organizer
    }

    public void listRegisteredLocations(String registeredLocations) {
        System.out.println(registeredLocations);
    }

    public void promptLocationMenu() {

        System.out.println("\nWhat would you do?");
        System.out.println("(1) See a location");
        System.out.println("(2) Add a location");
        System.out.println("(3) Remove a location");
        System.out.println("(4) Go back");

    }
}
