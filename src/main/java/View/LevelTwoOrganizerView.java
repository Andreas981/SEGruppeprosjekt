package View;

import Controller.SeatedEventController;
import Controller.LocationController;
import Controller.RoomController;
import Dummy.Database;
import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LevelTwoOrganizerView {

    private LocationController locationController = new LocationController();
    private RoomController roomController = new RoomController();
    private SeatedEventController seatedEventController = new SeatedEventController();
    private int currentLocation = -1;
    private int currentRoom = -1;
    private int currentEvent = -1;


    public void startView() {
        System.out.println("This is your registred locations: ");
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().size(); i++)
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName());

        System.out.println("\nWhat would you like to do?");
        System.out.println("\tType '1' See a location");
        System.out.println("\tType '2' Add location");
        System.out.println("\tType '3' Edit location");
        System.out.println("\tType '4' Remove a location");
        System.out.println("\tType '5' Sign out");

        int choice = 0;
        Boolean inputOk = true;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (Exception e){
            displayNotAnOption();
            inputOk = false;
        }

        if(inputOk){
            switch (choice){
                case 1:
                    seeLocation();
                    break;
                case 2:
                    addLocation();
                    break;
                case 3:
                    editLocation();
                    break;
                case 4:
                    removeLocation();
                    break;
                case 5:
                    return;
                default:
                    displayNotAnOption();
                    break;
            }
        }

        startView();
    }

    private void seeLocation(){
        System.out.println("\nWhich location do you want to see?");

        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }

        if(choice > -1 && locationController.checkExistance(choice)){
            currentLocation = choice;
            seeLocationView();
        }else {
            displaySomethingWentWrong();
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
            if(locationController.addLocation(name, address, publicLocaiton)){
                System.out.println("Location added!");
            }
        }else{
            displaySomethingWentWrong();
        }

    }
    private void removeLocation() {
        System.out.println("Which location do you want to remove?");

        int choice;
        try{
            choice = new Scanner(System.in).nextInt();
            System.out.println("Are you sure?");
            System.out.println("yes(y)/no(n)");

            String confirm = new Scanner(System.in).next();
            if (confirm.toLowerCase().startsWith("n")){
                return;
            }else if(confirm.toLowerCase().startsWith("y")){
                if(locationController.removeLocation(choice)){
                    System.out.println("Location removed!");
                }else{
                    displaySomethingWentWrong();
                }
            }
        }catch (InputMismatchException e){
            displayNotAnOption();
        }
    }
    private void editLocation(){
        System.out.println("\nWhich location do you want to edit?");

        int location = -1;
        try{
            location = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }

        if (location >  -1 && location < Database.currentLoggedInOrganizer.getLocations().size()){
            System.out.println("Type \".\" if you don't want to change a field");

            System.out.println("Name: ");
            String name = new Scanner(System.in).nextLine();

            System.out.println("Address: ");
            String address = new Scanner(System.in).nextLine();

            System.out.println("Public location (yes/no): ");
            String publicLocation = new Scanner(System.in).next();

            locationController.editLocaiton(location, name, address, publicLocation);

            System.out.println("Location edited!");
        }else{
            displaySomethingWentWrong();
        }

    }

    private void seeLocationView() {
        Boolean exit = false;
        System.out.println("\n****************************");
        System.out.println("Name: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getName() +
                "\nAddress: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getAddress() +
                "\nIs public: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getPublicLocation()
        );
        if (Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size() == 0){
            System.out.println("Thre is no registered rooms for this locaton");
        }else{
            System.out.println("Rooms for this location: ");
            for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size(); i++){
                System.out.println("\t(" + i + ")" + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(i).getName());
            }
        }
        System.out.println("****************************");


        System.out.println("\nWhat would you like to do?");
        System.out.println("\tType '1' See a room");
        System.out.println("\tType '2' Add a room");
        System.out.println("\tType '3' Edit a room");
        System.out.println("\tType '4' Remove a room");
        System.out.println("\tType '5' Go back");

        int choice = 0;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            seeLocationView();
        }
        switch (choice){
            case 1:
                seeRoom();
                break;
            case 2:
                addRoom();
                break;
            case 3:
                editRoom();
                break;
            case 4:
                removeRoom();
                break;
            case 5:
                return;
            default:
                displayNotAnOption();
                seeLocationView();
                break;
        }
        if(!exit)
            seeLocationView();

    }

    private void seeRoom(){
        System.out.println("\nWhich room do you want to see?");

        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }

        if(choice > -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size()){
            currentRoom = choice;
            seeRoomView();
        }else {
            displayNotAnOption();
            currentRoom = -1;
        }

    }
    private void addRoom() {
        System.out.println("\nRoom name: ");
        Scanner scanner = new Scanner(System.in);
        String name = new Scanner(System.in).nextLine();

        int max = -1;
        int rows  = -1;
        System.out.println("Max participents: ");
        try{
            max = scanner.nextInt();
        }catch (NumberFormatException e){
            displayNotAnOption();
            return;
        }
        System.out.println("Rows of seats in the room: ");
        try{
            rows = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }

        roomController.addRoom(currentLocation, name, max, rows);
        System.out.println("Room added!");

    }
    private void removeRoom() {
        System.out.println("\nWhich room do you want to remove?");

        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }
        System.out.println("Are you sure?");
        System.out.println("yes(y)/no(n)");

        String confirm = new Scanner(System.in).next();
        if(confirm.toLowerCase().startsWith("y")){
            if (choice > -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size()) {
                roomController.removeRoom(currentLocation, choice);
                System.out.println("Room removed");
            }else{
                displaySomethingWentWrong();
            }
        }

    }
    private void editRoom(){
        System.out.println("Which room do you want to edit?");
        int room;
        try{
            room = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }
        if(room >= 0 && room < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size()){

            System.out.println("Write \".\" if you dont want to change a field");
            System.out.println("Name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Max participents: ");
            String max = new Scanner(System.in).next();

            try{
                Integer.parseInt(max);
            }catch (NumberFormatException e){
                displayNotAnOption();
                return;
            }

            roomController.editRoom(currentLocation, room, name, max);

            System.out.println("Room edited!");
        }else{
            displayNotAnOption();
        }
    }

    private void seeRoomView(){
        Boolean exit = false;

        System.out.println("\n****************************");
        System.out.println("Name: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getName() +
                "\nMax participents: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getMaxParticipents() +
                "\nEvent in progress: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEventInProgress());
        System.out.println("Events in this room: ");
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().size(); i++)
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(i).getNameOfEvent());
        System.out.println("****************************");

        System.out.println("\nWhat would you like to do?");
        System.out.println("\tType '1' See event");
        System.out.println("\tType '2' Add event");
        System.out.println("\tType '3' Edit event");
        System.out.println("\tType '4' Remove event");
        System.out.println("\tType '5' Go back");

        int choice = 0;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            seeRoomView();
        }

        switch (choice){
            case 1:
                seeEvent();
                break;
            case 2:
                addEvent();
                break;
            case 3:
                editEvent();
                break;
            case 4:
                removeEvent();
                break;
            case 5:
                return;
            default:
                displayNotAnOption();
                seeRoomView();
                break;
        }
        if(!exit)
            seeRoomView();

    }

    private void seeEvent() {
        System.out.println("\nWhich event do you want to see");
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }
        if(choice > -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().size()){
            currentEvent = choice;
            seeEventView();
        }
        else{
            currentEvent = -1;
            displayNotAnOption();
        }
    }
    private void addEvent() {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        String patternForTime = "[0-9][0-9]-[0-9][0-9]";
        System.out.println("\nName");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Date of event (YYYY-MM-DD): ");
        String date = new Scanner(System.in).next();
        if(!seatedEventController.checkDate(date)){
            System.out.println("You cannot add an event in the past");
            return;
        }
        System.out.println("Start time of event (HH-MM): ");
        String time = new Scanner(System.in).next();
        int ageLimit = -1;
        int lengthOfEvent = -1;
        int price = -1;
        try {
            System.out.println("Length of event (in hours):");
            lengthOfEvent = new Scanner(System.in).nextInt();
            System.out.println("Age limit:");
            ageLimit = new Scanner(System.in).nextInt();
            System.out.println("Price (in nok): ");
            price = new Scanner(System.in).nextInt();

        } catch (InputMismatchException e) {
            displayNotAnOption();
            return;
        }

        if (Security.RegEx.regEx(patternForDate, date) && Security.RegEx.regEx(patternForTime, time) && ageLimit != -1){
            if(price < 1){
                wrongPriceInEvent(currentLocation, currentRoom, name, date, time, ageLimit, lengthOfEvent, price);
                return;
            }
            seatedEventController.addSeatedEvent(currentLocation, currentRoom, name, date, time, ageLimit, lengthOfEvent, price);
            System.out.println("Event added");
        }else{
            displayNotAnOption();
        }
    }
    private void editEvent() {
        System.out.println("Which event do you want to edit?");
        int event;
        try{
            event = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }
        if(event >= 0 && event < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().size()){

            System.out.println("Write \".\" if you dont want to change a field");
            System.out.println("Name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Date of event (YYYY-MM-DD): ");
            String date = new Scanner(System.in).next();
            if(!seatedEventController.checkDate(date)){
                System.out.println("You cannot add an event in the past");
                return;
            }
            System.out.println("Age limit");
            String ageLimit = new Scanner(System.in).next();
            try{
                Integer.parseInt(ageLimit);
            }catch (NumberFormatException e){
                displayNotAnOption();
                return;
            }
            System.out.println("Length of event:");
            String lengthOfEvent = new Scanner(System.in).next();
            try{
                Integer.parseInt(lengthOfEvent);
            }catch (NumberFormatException e){
                displayNotAnOption();
                return;
            }

            seatedEventController.editSeatedEvent(currentLocation, currentRoom, event, name, date, lengthOfEvent, ageLimit);

            System.out.println("Event edited!");
        }else{
            displayNotAnOption();
        }
    }
    private void removeEvent() {
        System.out.println("Which event do you want to remove?");
        int choice = -1;
        try{
            choice = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
        }
        if(choice > -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().size()){
            seatedEventController.removeSeatedEvent(currentLocation, currentRoom, choice);
            System.out.println("Event removed!");
        }else {
            displaySomethingWentWrong();
        }
    }

    private void wrongPriceInEvent(int currentLocation, int currentRoom, String name, String date, String time, int ageLimit, int lengthOfEvent, int price) {
        System.out.println("The price was not legal");
        System.out.println("Please enter a new one:");
        price = -1;
        try{
            price = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
        }
        if (price < 1){
            wrongPriceInEvent(currentLocation, currentRoom, name, date, time, ageLimit, lengthOfEvent, price);
        }else{
            seatedEventController.addSeatedEvent(currentLocation, currentRoom, name, date, time, ageLimit, lengthOfEvent, price);
            System.out.println("Event added");
        }
    }

    private void seeEventView(){
        System.out.println("\n****************************");
        System.out.println("\tName:      " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(currentEvent).getNameOfEvent());
        System.out.println("\tDate:      " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(currentEvent).getDateOfEvent().toLocalDate().toString() +
                " " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(currentEvent).getDateOfEvent().toLocalTime().toString());
        System.out.println("\tDurance:   " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(currentEvent).getLengthOfEvent());
        System.out.println("\tAge limit: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(currentEvent).getAgeLimit());
        System.out.println("\tPrice:     " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(currentEvent).getTickets().get(0).getPriceInNOK() + " kr");
        System.out.println("****************************");
    }

    private void displayNotAnOption(){
        System.out.println("Sorry, that is not an option");
    }
    private void displaySomethingWentWrong(){
        System.out.println("Invalid input entered");
    }
}
