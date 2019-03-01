package Controller;

import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenu {

    private View.OrganizerMenu organizerMenu = new View.OrganizerMenu();

    public void initOrganizerMenu(){
        if (Database.currentLoggedInOrganizer.getAccessLevel() == 1){
            //levelOneOrganizer();
        }else if(Database.currentLoggedInOrganizer.getAccessLevel() == 2){
            levelTwoOrganizer();
        }
    }
    private void levelTwoOrganizer() {

        Scanner scanner = new Scanner(System.in);

        organizerMenu.promptMenuLevelTwo();

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
                    levelTwoOrganizer();
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
            levelTwoOrganizer();
        }
    }

    private void addLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nName of location?");
        String name = scanner.next();
        System.out.println("Location address?");
        String address = scanner.next();
        System.out.println("Is it a public location? (1)yes (2)no");
        int publicLocaiton = -1;
        try{
            publicLocaiton = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
        }

        if(publicLocaiton == 1 || publicLocaiton == 2){
            if(Controller.Location.addLocation(name, address, publicLocaiton)){
                System.out.println("Location added");
                seeLocation();
            }else{
                System.out.println("Something went wrong");
                levelTwoOrganizer();
            }
        }

    }

    private void seeLocation() {

        String registeredLocations = "";

        System.out.println("\nThis is you're registered locations: ");
        for (int i = 0; i < Database.currentLoggedInOrganizer.getLocations().size(); i++){
            registeredLocations += "\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName() + "\n";
        }

        organizerMenu.listRegisteredLocations(registeredLocations);

        locationMenu();

    }

    private void locationMenu() {

        Scanner scanner = new Scanner(System.in);

        organizerMenu.promptLocationMenu();

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
                    //seeSpecificLocation(location);
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
                //removeLocation();
                break;
            case 4:
                //levelTwoOrganizer();
                break;

            default:
                System.out.println("Sorry, that is not an option");
                break;
        }

    }

}
