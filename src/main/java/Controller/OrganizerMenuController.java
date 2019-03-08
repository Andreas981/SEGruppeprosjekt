package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import Model.Room;
import Model.SeatedPlannedEvent;
import org.joda.time.LocalDateTime;

import java.util.InputMismatchException;
import java.util.Scanner;

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
                case 3:
                    Database.currentLoggedInOrganizer = null;
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
        organizerMenu.displayPromptForLocationName();
        String name = new Scanner(System.in).next();
        organizerMenu.displayPromptForLocationAddress();
        String address = new Scanner(System.in).next();
        organizerMenu.displayPromptForLocationPublic();
        int publicLocaiton = -1;
        try{
            publicLocaiton = new Scanner(System.in).nextInt();
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
                seeLocation();
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
            organizerMenu.displayPromptForStars();


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
        organizerMenu.displayPromptForEventsInRoom();
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().size(); i++){
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(i).getNameOfEvent());
        }
        organizerMenu.displayPromptForStars();
        roomMenu(location, room);
    }

    private void roomMenu(int location, int room) {
        Scanner scanner = new Scanner(System.in);
        organizerMenu.displayPromptForEventMenu();
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
                    organizerMenu.displayPromptForNotAnOption();
                    spesificRoom(location, room);
            }
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void seeEventPrompt(int location, int room) {
        organizerMenu.displayPromptForSeeEvent();
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
            seeEvent(location, room, choice);
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void seeEvent(int location, int room, int event) {
        organizerMenu.displayPromptForSeeEventDetail(location, room, event);
        spesificRoom(location, room);
    }

    private void removeEventPrompt(int location, int room) {
        organizerMenu.displayPromptForWhichEventToRemove();
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
            removeEvent(location, room, choice);
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void removeEvent(int location, int room, int event) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().remove(event);
        organizerMenu.displayPromptForEventRemoved();
        spesificRoom(location, room);
    }

    private void addEventPrompt(int location, int room) {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        String patternForTime = "[0-9][0-9]-[0-9][0-9]";
        organizerMenu.displayPromptForAddEventName();
        String name = new Scanner(System.in).nextLine();
        organizerMenu.displayPromptForAddEventDate();
        String date = new Scanner(System.in).next();
        organizerMenu.displayPromptForAddEventTime();
        String time = new Scanner(System.in).next();
        int ageLimit = -1;
        int lengthOfEvent = -1;
        try{
            organizerMenu.displayPromptForAddEventAgeLimit();
            ageLimit = new Scanner(System.in).nextInt();

        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }

        if (Security.RegEx.regEx(patternForDate, date) && Security.RegEx.regEx(patternForTime, time) && ageLimit != -1)
            addEvent(location, room, name, date, time, ageLimit, lengthOfEvent);
        else{
            organizerMenu.displayPromptForNotAnOption();
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

        organizerMenu.displayPromptForAddEventAdded();
        spesificRoom(location, room);
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
        seeSpecificLocation(location);
    }

    private void addRooomInput(int location) {
        try{
            organizerMenu.displayPromptForRoomName();
            String name = new Scanner(System.in).next();
            organizerMenu.displayPromptForMaxParticipents();
            int max = new Scanner(System.in).nextInt();
            addRoom(location, name, max);
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            spesificLocationMenu(location);
        }

    }

    private void addRoom(int location, String name, int max) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().add(new Room(name, max, false));
        organizerMenu.displayPromptForRoomAdded();
        seeSpecificLocation(location);
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
                    organizerMenu.displayPromptForNotAnOption();
                    levelOneOrganizer();
                    break;
            }

        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            levelOneOrganizer();
        }

    }

    private void addNonSeatedEvent() {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        organizerMenu.displayPromptForAddEventName();
        String name = new Scanner(System.in).nextLine();
        organizerMenu.displayPromptForAddEventDate();
        String date = new Scanner(System.in).next();
        int length = -1;
        int ageLimit = -1;
        int freeSpace = -1;
        try{
            organizerMenu.displayPromptForAddEventLength();
            length = new Scanner(System.in).nextInt();
            organizerMenu.displayPromptForAddEventAgeLimit();
            ageLimit = new Scanner(System.in).nextInt();
            System.out.println("Space availible: ");
            freeSpace = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            organizerMenu.displayPromptForNotAnOption();
            levelOneOrganizer();
        }
        System.out.println("Is the event free? yes/no");
        String freeEvent = new Scanner(System.in).next();
        System.out.println("Meet up: ");
        String meetUp = new Scanner(System.in).next();

        if (name.length() > 0 && Security.RegEx.regEx(patternForDate, date) && length != -1 && ageLimit != -1 && freeSpace != -1 && (freeEvent.split("")[0].toLowerCase().equals("n")||freeEvent.split("")[0].toLowerCase().equals("y")) && meetUp.length() > 0){
            String[] dateArray = date.split("-");
            Database.currentLoggedInOrganizer.addNonSeatedPlannedEvent(new NonSeatedPlannedEvent(name, new LocalDateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), 00, 00), length, ageLimit, freeSpace, meetUp, (freeEvent.split("")[0].toLowerCase().equals("y"))));
            organizerMenu.displayPromptForAddEventAdded();
            levelOneOrganizer();
        }


    }

}
