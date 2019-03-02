import Model.Organizer;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LocationController {

    Organizer organizer;

    @Before
    public void init(){
        organizer = new Organizer("Andreas", "Mikalsen", "andremi@hiof.no", "97781816", "andremi","abc123", new LocalDate(1998-1900, 6-1, 12), "HiØ", 1);
    }

    @Test
    public void addingLocationToOrganizerAndGettingTheName(){
        organizer.addLocation(new Model.Location("TestLocation", "Hjemme", true));
        Assert.assertEquals("TestLocation", organizer.getLocations().get(0).getName());
    }
}
