package Controller;

import Dummy.Database;
import Model.Organizer;
import Model.Room;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenu {

    private View.OrganizerMenu organizerMenu = new View.OrganizerMenu();

    public void initOrganizerMenu(){
        if (Database.currentLoggedInOrganizer.getAccessLevel() == 1){
            levelOneOrganizer();
        }else if(Database.currentLoggedInOrganizer.getAccessLevel() == 2){
            levelTwoOrganizer();
        }
    }
    private void levelTwoOrganizer() {

        Scanner scanner = new Scanner(System.in);

        organizerMenu.displayPromptForLevelTwoOrganizer();

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
                    organizerMenu.displayPromptForNotAnOption();
                    levelTwoOrganizer();
                    break;
            }
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            levelTwoOrganizer();
        }
    }

    private void addLocation() {
        Scanner scanner = new Scanner(System.in);
        organizerMenu.displayPromptForLocationName();
        String name = scanner.next();
        organizerMenu.displayPromptForLocationAddress();
        String address = scanner.next();
        organizerMenu.displayPromptForLocationPublic();
        int publicLocaiton = -1;
        try{
            publicLocaiton = scanner.nextInt();
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
        }

        if(publicLocaiton == 1 || publicLocaiton == 2){
            if(Controller.Location.addLocation(name, address, publicLocaiton)){
                organizerMenu.displayPromptForLocationAdded();
                seeLocation();
            }else{
                organizerMenu.displayPromptForSomethingWentWrong();
                levelTwoOrganizer();
            }
        }

    }

    private void seeLocation() {

        String registeredLocations = "";

        organizerMenu.displayPromptForRegisteredLocation();
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
            organizerMenu.displayPromptForNotAnOption();
            seeLocation();
        }

        switch (choice){
            case 1:
                //See specific locaiton
                organizerMenu.displayPromptForSpesificLocation();
                int location;
                try{
                    location = scanner.nextInt();
                    seeSpecificLocation(location);
                }catch (InputMismatchException e){
                    organizerMenu.displayPromptForNotAnOption();
                    seeLocation();
                }

                break;
            case 2:
                //Add location
                addLocation();
                break;
            case 3:
                //Remove location
                removeLocation();
                break;
            case 4:
                levelTwoOrganizer();
                break;

            default:
                organizerMenu.displayPromptForNotAnOption();
                break;
        }

    }

    private void removeLocation() {
        Scanner scanner = new Scanner(System.in);
        organizerMenu.displayPromptForRemovingLocation();
        int choice;
        try{
            choice = scanner.nextInt();
            organizerMenu.displayPromptForAreYouSure();
            String confirm = scanner.next();
            if (confirm.toLowerCase().startsWith("n")){
                locationMenu();
            }else if(confirm.toLowerCase().startsWith("y")){
                if(choice >= 0 && choice <= Database.currentLoggedInOrganizer.getLocations().size()){
                    Database.currentLoggedInOrganizer.getLocations().remove(choice);
                    organizerMenu.displayPromptForLocationDeleted();
                    seeLocation();
                }
            }
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            seeLocation();
        }
    }
    private void seeSpecificLocation(int location) {
        if (location >= 0 && location <= Database.currentLoggedInOrganizer.getLocations().size()){

            organizerMenu.displayPromptForSpesicficLocationDetails(location);
            if (Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() == 0){
                organizerMenu.displayPromptForNoRegisteredRooms();
            }else{
                organizerMenu.displayPromptForRoomsInSpesificLocation();
                for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size(); i++){
                    System.out.println("\t(" + i + ")" + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(i).getName());
                }
            }


            //TODO Add functionality to Rooms within a location
            spesificLocationMenu(location);


        }else{
            organizerMenu.displayPromptForNotAnOption();
            seeLocation();
        }
    }

    private void spesificLocationMenu(int location) {
        int choice = -1;
        organizerMenu.displayPromptForSpesificLocationMenu();
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            seeSpecificLocation(location);
        }
        switch (choice){
            case 0:
                break;
            case 1:
                deleteRoom(location);
                break;
            case 2:
                addRooomInput(location);
                break;
            case 3:
                break;
            case 4:
                seeLocation();
                break;
            default:
                organizerMenu.displayPromptForNotAnOption();
                spesificLocationMenu(location);
        }
    }

    private void deleteRoom(int location) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        organizerMenu.displayPromptForWhichRoomToDelete();
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
        }
        if(Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() != 0 && choice < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() && choice > -1)
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().remove(choice);
        organizerMenu.displayPromptForRoomRemoved();
        spesificLocationMenu(location);
    }

    private void addRooomInput(int location) {
        Scanner scanner = new Scanner(System.in);
        try{
            organizerMenu.displayPromptForRoomName();
            String name = scanner.next();
            organizerMenu.displayPromptForMaxParticipents();
            int max = scanner.nextInt();
            addRoom(location, name, max);
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificLocationMenu(location);
        }

    }

    private void addRoom(int location, String name, int max) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().add(new Room(name, max, false));
        organizerMenu.displayPromptForRoomAdded();
        spesificLocationMenu(location);
    }


    private static void levelOneOrganizer() {
        //TODO Menu for level one organizer
    }

}
