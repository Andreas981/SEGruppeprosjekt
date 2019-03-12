package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import Model.Room;
import Model.SeatedPlannedEvent;
import View.OrganizerMenuView;
import org.joda.time.LocalDateTime;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenuController {

    private OrganizerMenuView organizerMenuView = new OrganizerMenuView();

    public void initOrganizerMenu(){
        if (Database.currentLoggedInOrganizer.getAccessLevel() == 1){
            levelOneOrganizer();
        }else if(Database.currentLoggedInOrganizer.getAccessLevel() == 2){
            levelTwoOrganizer();
        }
    }
    private void levelTwoOrganizer() {

        Scanner scanner = new Scanner(System.in);
        organizerMenuView.displayPromptForLevelTwoOrganizer();

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
                    organizerMenuView.displayPromptForNotAnOption();
                    levelTwoOrganizer();
                    break;
            }
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            levelTwoOrganizer();
        }
    }

    private void addLocation() {
        organizerMenuView.displayPromptForLocationName();
        String name = new Scanner(System.in).next();
        organizerMenuView.displayPromptForLocationAddress();
        String address = new Scanner(System.in).next();
        organizerMenuView.displayPromptForLocationPublic();
        int publicLocaiton = -1;
        try{
            publicLocaiton = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
        }

        if(publicLocaiton == 1 || publicLocaiton == 2){
            if(LocationController.addLocation(name, address, publicLocaiton)){
                organizerMenuView.displayPromptForLocationAdded();
                seeLocation();
            }else{
                organizerMenuView.displayPromptForSomethingWentWrong();
                levelTwoOrganizer();
            }
        }

    }

    private void seeLocation() {

        String registeredLocations = "";

        organizerMenuView.displayPromptForRegisteredLocation();
        for (int i = 0; i < Database.currentLoggedInOrganizer.getLocations().size(); i++){
            registeredLocations += "\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName() + "\n";
        }

        organizerMenuView.listRegisteredLocations(registeredLocations);

        locationMenu();

    }

    private void locationMenu() {

        Scanner scanner = new Scanner(System.in);

        organizerMenuView.promptLocationMenu();

        int choice = 0;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            seeLocation();
        }

        switch (choice){
            case 1:
                //See specific locaiton
                organizerMenuView.displayPromptForSpesificLocation();
                int location;
                try{
                    location = scanner.nextInt();
                    seeSpecificLocation(location);
                }catch (InputMismatchException e){
                    organizerMenuView.displayPromptForNotAnOption();
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
                organizerMenuView.displayPromptForNotAnOption();
                seeLocation();
                break;
        }

    }

    private void removeLocation() {
        Scanner scanner = new Scanner(System.in);
        organizerMenuView.displayPromptForRemovingLocation();
        int choice;
        try{
            choice = scanner.nextInt();
            organizerMenuView.displayPromptForAreYouSure();
            String confirm = scanner.next();
            if (confirm.toLowerCase().startsWith("n")){
                locationMenu();
            }else if(confirm.toLowerCase().startsWith("y")){
                if(choice >= 0 && choice <= Database.currentLoggedInOrganizer.getLocations().size()){
                    Database.currentLoggedInOrganizer.getLocations().remove(choice);
                    organizerMenuView.displayPromptForLocationDeleted();
                    seeLocation();
                }
            }
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            seeLocation();
        }
    }
    private void seeSpecificLocation(int location) {
        if (location >= 0 && location <= Database.currentLoggedInOrganizer.getLocations().size()){

            organizerMenuView.displayPromptForSpesicficLocationDetails(location);
            if (Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() == 0){
                organizerMenuView.displayPromptForNoRegisteredRooms();
            }else{
                organizerMenuView.displayPromptForRoomsInSpesificLocation();
                for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size(); i++){
                    System.out.println("\t(" + i + ")" + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(i).getName());
                }
            }
            organizerMenuView.displayPromptForStars();


            spesificLocationMenu(location);


        }else{
            organizerMenuView.displayPromptForNotAnOption();
            seeLocation();
        }
    }

    private void spesificLocationMenu(int location) {
        int choice = -1;
        organizerMenuView.displayPromptForSpesificLocationMenu();
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
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
                organizerMenuView.displayPromptForNotAnOption();
                spesificLocationMenu(location);
        }
    }

    private void editRoom(int location) {
        Scanner scanner = new Scanner(System.in);
        organizerMenuView.displayPromptForWhichRoomToEdit();
        int room = -1;
        try{
            room = scanner.nextInt();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            seeSpecificLocation(location);
        }
        if(room >= 0 && room < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size()){

            organizerMenuView.displayPromptForNotEditField();
            organizerMenuView.displayPromptForNewRoomName();
            String name = new Scanner(System.in).nextLine();
            int max = -1;
            try{
                organizerMenuView.displayPromptForNewMaxParticipents();
                max = scanner.nextInt();
            }catch (InputMismatchException e){
                organizerMenuView.displayPromptForNotAnOption();
                seeSpecificLocation(location);
            }
            if(!name.equals("-1"))
                Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).setName(name);
            if(max >= 0)
                Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).setMaxParticipents(max);
            organizerMenuView.displayPromptForRoomEdited();
            seeSpecificLocation(location);
        }else{
            organizerMenuView.displayPromptForNotAnOption();
            seeSpecificLocation(location);
        }
    }

    private void seeRoom(int location) {
        Scanner scanner = new Scanner(System.in);
        organizerMenuView.displayPromptForSeeingSpesificRoom();
        int choice = -1;
        try{
            choice = scanner.nextInt();
            if(choice != -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() && choice > -1)
                spesificRoom(location, choice);
            else
                organizerMenuView.displayPromptForNotAnOption();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
        }
    }

    private void spesificRoom(int location, int room) {
        organizerMenuView.displayPromptForRoomDetails(location, room);
        organizerMenuView.displayPromptForEventsInRoom();
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().size(); i++){
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(i).getNameOfEvent());
        }
        organizerMenuView.displayPromptForStars();
        roomMenu(location, room);
    }

    private void roomMenu(int location, int room) {
        Scanner scanner = new Scanner(System.in);
        organizerMenuView.displayPromptForEventMenu();
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
                    organizerMenuView.displayPromptForNotAnOption();
                    spesificRoom(location, room);
            }
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void seeEventPrompt(int location, int room) {
        organizerMenuView.displayPromptForSeeEvent();
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
            seeEvent(location, room, choice);
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void seeEvent(int location, int room, int event) {
        organizerMenuView.displayPromptForSeeEventDetail(location, room, event);
        spesificRoom(location, room);
    }

    private void removeEventPrompt(int location, int room) {
        organizerMenuView.displayPromptForWhichEventToRemove();
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
            removeEvent(location, room, choice);
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }
    }

    private void removeEvent(int location, int room, int event) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().remove(event);
        organizerMenuView.displayPromptForEventRemoved();
        spesificRoom(location, room);
    }

    private void addEventPrompt(int location, int room) {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        String patternForTime = "[0-9][0-9]-[0-9][0-9]";
        organizerMenuView.displayPromptForAddEventName();
        String name = new Scanner(System.in).nextLine();
        organizerMenuView.displayPromptForAddEventDate();
        String date = new Scanner(System.in).next();
        organizerMenuView.displayPromptForAddEventTime();
        String time = new Scanner(System.in).next();
        int ageLimit = -1;
        int lengthOfEvent = -1;
        try{
            organizerMenuView.displayPromptForAddEventAgeLimit();
            ageLimit = new Scanner(System.in).nextInt();

        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificRoom(location, room);
        }

        if (Security.RegEx.regEx(patternForDate, date) && Security.RegEx.regEx(patternForTime, time) && ageLimit != -1)
            addEvent(location, room, name, date, time, ageLimit, lengthOfEvent);
        else{
            organizerMenuView.displayPromptForNotAnOption();
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

        organizerMenuView.displayPromptForAddEventAdded();
        spesificRoom(location, room);
    }

    private void deleteRoom(int location) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        organizerMenuView.displayPromptForWhichRoomToDelete();
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
        }
        if(Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() != 0 && choice < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().size() && choice > -1)
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().remove(choice);
        organizerMenuView.displayPromptForRoomRemoved();
        seeSpecificLocation(location);
    }

    private void addRooomInput(int location) {
        try{
            organizerMenuView.displayPromptForRoomName();
            String name = new Scanner(System.in).next();
            organizerMenuView.displayPromptForMaxParticipents();
            int max = new Scanner(System.in).nextInt();
            addRoom(location, name, max);
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            spesificLocationMenu(location);
        }

    }

    private void addRoom(int location, String name, int max) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().add(new Room(name, max, false));
        organizerMenuView.displayPromptForRoomAdded();
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
                    organizerMenuView.displayPromptForNotAnOption();
                    levelOneOrganizer();
                    break;
            }

        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            levelOneOrganizer();
        }

    }

    private void addNonSeatedEvent() {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        organizerMenuView.displayPromptForAddEventName();
        String name = new Scanner(System.in).nextLine();
        organizerMenuView.displayPromptForAddEventDate();
        String date = new Scanner(System.in).next();
        int length = -1;
        int ageLimit = -1;
        int freeSpace = -1;
        try{
            organizerMenuView.displayPromptForAddEventLength();
            organizerMenuView.displayPromptForAddEventLength();
            length = new Scanner(System.in).nextInt();
            organizerMenuView.displayPromptForAddEventAgeLimit();
            ageLimit = new Scanner(System.in).nextInt();
            System.out.println("Space availible: ");
            freeSpace = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            organizerMenuView.displayPromptForNotAnOption();
            levelOneOrganizer();
        }
        System.out.println("Is the event free? yes/no");
        String freeEvent = new Scanner(System.in).next();
        System.out.println("Meet up: ");
        String meetUp = new Scanner(System.in).next();

        if (name.length() > 0 && Security.RegEx.regEx(patternForDate, date) && length != -1 && ageLimit != -1 && freeSpace != -1 && (freeEvent.split("")[0].toLowerCase().equals("n")||freeEvent.split("")[0].toLowerCase().equals("y")) && meetUp.length() > 0){
            String[] dateArray = date.split("-");
            Database.currentLoggedInOrganizer.addNonSeatedPlannedEvent(new NonSeatedPlannedEvent(name, new LocalDateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), 00, 00), length, ageLimit, freeSpace, meetUp, (freeEvent.split("")[0].toLowerCase().equals("y"))));
            organizerMenuView.displayPromptForAddEventAdded();
            levelOneOrganizer();
        }


    }

}
