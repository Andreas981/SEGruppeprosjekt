package Model;


import java.sql.Date;

public abstract class User {
    String firstName, lastName, mail, telephone, username, password;
    Date birthday;

    public User(String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.telephone = telephone;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
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
