package Model;

import java.sql.Date;

public class Organizer extends User{

    String organization;
    int accessLevel;

    public Organizer(String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday, String organization, int  accessLevel) {
        super(firstName, lastName, mail, telephone, username, password, birthday);
        this.organization = organization;
        this.accessLevel = accessLevel;
    }
    
    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public int getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
