package Dummy;


import Model.Customer;
import Model.Organizer;

import java.sql.Date;

public class InitStart {
    public static void Init(){

        //Setting up organizers
        Database.organizers.add(new Organizer("Kari", "Normann", "kari@normann.no", "12345678", "karino", "NeedHashing", new Date(1976-1900, 5, 23), "HiØ", 1));
        Database.organizers.add(new Organizer("Ole", "Olsen", "Ole.12@gmail.no", "98765432", "oleol", "StillNeedHashing", new Date(1985-1900, 3, 12), "Thon Hotell", 2));

        //Setting up customer
        Database.customers.add(new Customer("Per", "Persen", "per@persen.com", "11223344", "persen", "WhyWeNoHash?", new Date(1995-1900, 7, 15)));


        



    }
}
