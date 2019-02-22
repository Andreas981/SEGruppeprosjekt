package Model;


import java.sql.Date;

public abstract class User {
    String firstName, lastName;
    Date birthday;

    public User(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        System.out.println("Bday: " + birthday);
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
