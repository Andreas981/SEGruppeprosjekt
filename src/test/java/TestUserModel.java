import Model.Organizer;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;


public class TestUserModel {

    Organizer organizer;

    @Before
    public void settingUpOrganizer(){
        organizer = new Organizer("Andreas", "Mikalsen", "andremi@hiof.no", "97781816", "andremi","abc123", new LocalDate(1998,6,1), "HiØ", 1);
    }
    @Test
    public void gettingFullNameOfOrganizer(){
        Assert.assertEquals("Andreas Mikalsen", organizer.getFullName());
    }
    @Test
    public void gettingWringNameFromOrganizer(){
        Assert.assertNotEquals("HAHAHA", organizer.getFullName());
    }
    @Test
    public void gettingAccessLevelFromOrganizer(){
        Assert.assertEquals(1, organizer.getAccessLevel());
    }
    @Test
    public void gettingUserNameFromOrganizer(){
        Assert.assertEquals("andremi", organizer.getUsername());
    }
    @Test
    public void gettingMailAddressFromOrganizer(){
        Assert.assertEquals("andremi@hiof.no", organizer.getMail());
    }


}
