package Dummy;

import Model.Customer;
import Model.Organizer;
import Model.User;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Organizer> organizers = new ArrayList<Organizer>();
    public static ArrayList<Customer> customers = new ArrayList<Customer>();

    public static User currentLoggedInUser = null;

}
