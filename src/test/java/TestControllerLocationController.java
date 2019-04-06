import Controller.LocationController;
import Dummy.Database;
import Model.Location;
import Model.Organizer;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestControllerLocationController {

    Location location;
    Organizer organizer;
    LocationController locationController;

    @Before
    public void init(){
        location = new Location("test name","test address",false);
        organizer = new Organizer("Andreas", "Mikalsen", "andremi@hiof.no",
                "97781816", "andremi","abc123", new LocalDate(1998-1900, 6-1, 12),
                "HiØ", 1);
        Database.currentLoggedInOrganizer = organizer;
        Database.currentLoggedInOrganizer.addLocation(location);
        locationController = new LocationController();
    }

    @Test
    public void testAddingLocationToOrganizerAndGettingTheName(){
        Assert.assertTrue(locationController.addLocation("test name","test address",1));
        Assert.assertEquals("test name", organizer.getLocations().get(0).getName());
    }

    @Test
    public void userChangesNameOfLocation(){
        Assert.assertEquals("test name", organizer.getLocations().get(0).getName());
        // User selects this event, and changes the name
        locationController.editLocaiton(0,"test name edit","test",
                "y");
        Assert.assertEquals("test name edit",organizer.getLocations().get(0).getName());
    }

    @Test
    public void checkIfThereIsAlocation(){
        Assert.assertTrue(locationController.checkExistance(0));
    }

    @Test
    public void selectInvalidLocation(){
        Assert.assertFalse(locationController.checkExistance(1));
    }

    @Test
    public void removeLocationFromOrganizer(){
        // Organizer has one location:
        Assert.assertEquals(1,organizer.getLocations().size());
        // Remove current location from organizer:
        Assert.assertTrue(locationController.removeLocation(0));
    }
}
