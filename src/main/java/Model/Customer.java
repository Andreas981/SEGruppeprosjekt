package Model;

import java.sql.Date;

public class Customer extends User {


    public Customer(String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday) {
        super(firstName, lastName, mail, telephone, username, password, birthday);
    }



}
