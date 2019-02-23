package Dummy;


import Model.Customer;
import Model.Location;
import Model.Organizer;
import Model.Room;

import java.sql.Date;

public class InitStart {
    public static void Init(){

        //Setting up organizers
        Database.organizers.add(new Organizer("Kari", "Normann", "kari@normann.no", "12345678", "karino", "NeedHashing", new Date(1976-1900, 5, 23), "HiØ", 1));
        Database.organizers.add(new Organizer("Ole", "Olsen", "Ole.12@gmail.no", "98765432", "oleol", "StillNeedHashing", new Date(1985-1900, 3, 12), "Thon Hotell", 2));

        //Setting up customer
        Database.customers.add(new Customer("Per", "Persen", "per@persen.com", "11223344", "persen", "WhyWeNoHash?", new Date(1995-1900, 7, 15)));


        //Setting up locations for Kari Normann
        Database.organizers.get(0).addLocation(new Location("Høgskolen i Østfold", "Veien 12", true));
        //Setting up locations for Ole Olsen
        Database.organizers.get(1).addLocation(new Location("Thon Hotell Ski", "SkiVeien 123", true));
        Database.organizers.get(1).addLocation(new Location("Thon Hotell Oslo", "OsloVeien 1", true));

        //Setting up rooms for Kari Normann
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud Max", 400, false));
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud 4", 200, false));
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("D1-058", 50, true));

        



    }
}
