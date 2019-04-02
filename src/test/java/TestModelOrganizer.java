import Dummy.Database;
import Model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestModelOrganizer {

    Organizer organizer;
    Location location;
    NonSeatedPlannedEvent nonSeatedPlannedEvent;

    @Before
    public void initTests(){
        organizer = new Organizer("Per", "Persen", "Mail", "wewewe", "username", "password", new LocalDate(2000,2,2), "Org", 2);
        location = new Location("test name","test address",false);
        nonSeatedPlannedEvent = new NonSeatedPlannedEvent("test name",new LocalDateTime(1990,1,1,1,1),1,1,0,"test address",true,1);
        Database.currentLoggedInOrganizer = organizer;
        organizer.addLocation(location);
        organizer.addNonSeatedPlannedEvent(nonSeatedPlannedEvent);
    }
    /* Level 1 organizer*/
    @Test
    public void testAddLocation(){
        /*Object already been added in init*/
        Assert.assertNotNull(organizer.getLocations().get(0));
    }

    @Test
    public void testGetLocation(){
        Assert.assertNotNull(organizer.getLocations().get(0));
    }

    @Test
    public void testAddNonSeatedPlannedEvent(){
        /*Object already been added in init*/
        Assert.assertNotNull(organizer.getNonSeatedPlannedEvents().get(0));
    }

    @Test
    public void testGetNonSeatedPlannedEvents(){
        Assert.assertNotNull(organizer.getNonSeatedPlannedEvents().get(0));
    }

    @Test
    public void testGetOrganization(){

    }

    @Test
    public void testSetOrganization(){

    }

    @Test
    public void testGetAccessLevel(){

    }

    @Test
    public void testSetAccessLevel(){

    }

}
