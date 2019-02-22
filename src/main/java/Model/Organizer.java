package Model;

import java.sql.Date;

public class Organizer extends User{

    String organization;
    int accessLevel;

    public Organizer(String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday) {
        super(firstName, lastName, mail, telephone, username, password, birthday);
    }
}
