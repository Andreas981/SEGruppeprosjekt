package Model;

import org.junit.Assert;
import org.junit.Test;

public class User {

    @Test
    public void gettingFullNameOfOrganizer(){

        Organizer organizer = new Organizer("Andreas", "Mikalsen");
        Assert.assertEquals("Andreas Mikalsen", organizer.getFullName());

    }

}
