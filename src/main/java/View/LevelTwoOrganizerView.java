package View;

import Controller.LevelTwoOrganizerController;
import Controller.LocationController;
import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LevelTwoOrganizerView {

    private Scanner scanner = new Scanner(System.in);
    private LevelTwoOrganizerController organizerController = new LevelTwoOrganizerController();

    public void startView() {
        System.out.println("This is your registred locations: ");
        organizerController.printLocationForCurrenOrganizer();

        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(1) See a location");
        System.out.println("\t(2) Add location");
        System.out.println("\t(3) Remove a location");
        System.out.println("\t(4) Sign out");

        int choice = 0;
        try {
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    seeLocation();
                    break;
                case 2:
                    addLocation();
                    break;
                case 3:
                    removeLocation();
                    break;
                case 4:
                    Database.currentLoggedInOrganizer = null;
                    return;
                default:
                    displayNotAnOption();
                    break;
            }
        }catch (InputMismatchException e){
            displayNotAnOption();
        }
        startView();
    }


    private void seeLocation(){
        System.out.println("\nWhich location do you want to see?");

        int choice = -1;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            startView();
        }

        if(choice > -1 && LocationController.checkExistance(choice)){
            seeLocationView(choice);
        }else {
            displaySomethingWentWrong();
            startView();
        }

    }
    private void removeLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which location do you want to remove?");

        int choice;
        try{
            choice = scanner.nextInt();
            System.out.println("Are you sure?");
            System.out.println("yes(y)/no(n)");

            String confirm = scanner.next();
            if (confirm.toLowerCase().startsWith("n")){
                startView();
            }else if(confirm.toLowerCase().startsWith("y")){
                if(LocationController.removeLocation(choice)){
                    System.out.println("Location removed!");
                }else{
                    displaySomethingWentWrong();
                }
                startView();
            }
        }catch (InputMismatchException e){
            displayNotAnOption();
            startView();
        }
    }
    private void addLocation() {
        System.out.println("\nName of location?");
        String name = new Scanner(System.in).next();
        System.out.println("Location address?");
        String address = new Scanner(System.in).next();
        System.out.println("Is it a public location? (1)yes (2)no");
        int publicLocaiton = -1;
        try{
            publicLocaiton = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
        }

        if(publicLocaiton == 1 || publicLocaiton == 2){
            if(LocationController.addLocation(name, address, publicLocaiton)){
                System.out.println("Location added!");
                startView();
            }else{
                displaySomethingWentWrong();
                startView();
            }
        }

    }

    private void seeLocationView(int location) {
        System.out.println("\n****************************");
        System.out.println("Name: " + Database.currentLoggedInOrganizer.getLocations().get(location).getName() +
                "\nAddress: " + Database.currentLoggedInOrganizer.getLocations().get(location).getAddress() +
                "\nIs public: " + Database.currentLoggedInOrganizer.getLocations().get(location).getPublicLocation()
        );
        if (Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() == 0){
            System.out.println("Thre is no registered rooms for this locaton");
        }else{
            System.out.println("Rooms for this location: ");
            for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size(); i++){
                System.out.println("\t(" + i + ")" + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(i).getName());
            }
        }
        System.out.println("****************************");

        




    }

    private void displayNotAnOption(){
        System.out.println("Sorry, that is not an option");
    }
    private void displaySomethingWentWrong(){
        System.out.println("Something went wrong");
    }
}
