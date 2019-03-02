package Dummy;


import Model.*;

import java.sql.Time;
import java.util.Date;

public class InitStart {
    public static void Init(){

        // Setting up organizers
        Database.organizers.add(new Organizer("Kari", "Normann", "kari@normann.no", "12345678", "karino", Security.PassHash.hashPassword("abc123"), new Date(1976-1900, 5, 23), "HiØ", 2));
        Database.organizers.add(new Organizer("Ole", "Olsen", "Ole.12@gmail.no", "98765432", "oleol", Security.PassHash.hashPassword("abc123"), new Date(1985-1900, 3, 12), "Thon Hotell", 1));


        // Setting up customer
        Database.customers.add(new Customer("Per", "Persen", "per@persen.com", "11223344", "persen", Security.PassHash.hashPassword("abc123"), new Date(1995-1900, 7, 15)));


        // Setting up locations for Kari Normann
        Database.organizers.get(0).addLocation(new Location("Høgskolen i Østfold", "Veien 12", true));
        // Setting up locations for Ole Olsen
        Database.organizers.get(1).addLocation(new Location("Thon Hotell Ski", "SkiVeien 123", true));
        Database.organizers.get(1).addLocation(new Location("Thon Hotell Oslo", "OsloVeien 1", true));

        // Setting up rooms for Kari Normann
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud Max", 400, false));
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud 4", 200, false));
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("D1-058", 50, true));

        // Dummy event for HIØ Playing at Aud Max
        Database.organizers.get(0).getLocations().get(0).getRooms()
                .get(0).addEvent(new SeatedPlannedEvent("Example seated event",new Date(1988,3,2)
                ,new Time(22),
                100,18));




    }
}
