package Model;

import org.joda.time.LocalDate;

public class Customer extends User {

    public Customer(String firstName, String lastName, String mail, String telephone, String username, String password, LocalDate birthday) {
        super(firstName, lastName, mail, telephone, username, password, birthday);
    }
}
