package Controller;

import Dummy.Database;
import Model.Room;
import org.joda.time.LocalDateTime;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrganizerMenuController {

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
            if(LocationController.addLocation(name, address, publicLocaiton)){
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
                seeRoom(location);
                break;
            case 1:
                deleteRoom(location);
                break;
            case 2:
                addRooomInput(location);
                break;
            case 3:
                editRoom(location);
                break;
            case 4:
                seeLocation();
                break;
            default:
                organizerMenu.displayPromptForNotAnOption();
                spesificLocationMenu(location);
        }
    }

    private void editRoom(int location) {
        Scanner scanner = new Scanner(System.in);
        organizerMenu.displayPromptForWhichRoomToEdit();
        int room = -1;
        try{
            room = scanner.nextInt();
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            seeSpecificLocation(location);
        }
        if(room >= 0 && room < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size()){

            organizerMenu.displayPromptForNotEditField();
            organizerMenu.displayPromptForNewRoomName();
            String name = new Scanner(System.in).nextLine();
            int max = -1;
            try{
                organizerMenu.displayPromptForNewMaxParticipents();
                max = scanner.nextInt();
            }catch (InputMismatchException e){
                organizerMenu.displayPromptForNotAnOption();
                seeSpecificLocation(location);
            }
            if(!name.equals("-1"))
                Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).setName(name);
            if(max >= 0)
                Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).setMaxParticipents(max);
            organizerMenu.displayPromptForRoomEdited();
            seeSpecificLocation(location);
        }else{
            organizerMenu.displayPromptForNotAnOption();
            seeSpecificLocation(location);
        }
    }

    private void seeRoom(int location) {
        Scanner scanner = new Scanner(System.in);
        organizerMenu.displayPromptForSeeingSpesificRoom();
        int choice = -1;
        try{
            choice = scanner.nextInt();
            if(choice != -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() && choice > -1)
                spesificRoom(location, choice);
            else
                organizerMenu.displayPromptForNotAnOption();
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
        }
    }

    private void spesificRoom(int location, int room) {
        organizerMenu.displayPromptForRoomDetails(location, room);
        System.out.println("Events in this room: ");
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().size(); i++){
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(i).getNameOfEvent());
        }
        roomMenu(location, room);
    }

    private void roomMenu(int location, int room) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(0) See event");
        System.out.println("\t(1) Add event");
        System.out.println("\t(2) Remove event");
        System.out.println("\t(3) Edit event");
        System.out.println("\t(4) Go back");
        int choice = -1;
        try{
            choice = scanner.nextInt();
            switch (choice){
                case 0:

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
                    organizerMenu.displayPromptForNotAnOption();
                    spesificRoom(location, room);
            }
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void removeEventPrompt(int location, int room) {
        System.out.println("Which event do you want to remove?");
        int choice = -1;
        try{

        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void addEventPrompt(int location, int room) {
        //String nameOfEvent, String dateOfEvent, Time timeOfEventStart, int lengthOfEvent, int ageLimit
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        String patternForTime = "[0-9][0-9]-[0-9][0-9]";
        System.out.println("Name of event: ");
        String name = new Scanner(System.in).nextLine();
        //TODO Add Joda date to event
        System.out.println("Date of event (YYYY-MM-DD): ");
        String date = new Scanner(System.in).next();
        System.out.println("Start time of event (HH-MM): ");
        String time = new Scanner(System.in).next();
        int ageLimit = -1;
        int lengthOfEvent = -1;
        try{
            System.out.println("Age limit");
            ageLimit = new Scanner(System.in).nextInt();

        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }

        if (Security.RegEx.regEx(patternForDate, date) && Security.RegEx.regEx(patternForTime, time) && ageLimit != -1)
            addEvent(name, date, time, ageLimit);



    }

    private void addEvent(String name, String date, String time, int ageLimit) {
        //TODO Add event
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
