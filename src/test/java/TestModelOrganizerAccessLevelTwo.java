import Dummy.Database;
import Model.Location;
import Model.NonSeatedPlannedEvent;
import Model.Organizer;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestModelOrganizerAccessLevelTwo {

    Organizer organizerLevelTwo;
    Location location;
    NonSeatedPlannedEvent nonSeatedPlannedEvent;

    @Before
    public void initTests(){
        organizerLevelTwo = new Organizer("test name", "test lastname", "test mail",
                "12312312", "test username", "test password",
                new LocalDate(1990,1,1), "test org", 2);
        location = new Location("test name","test address",false);
        nonSeatedPlannedEvent = new NonSeatedPlannedEvent("test name",new LocalDateTime(1990,1,1,1,1),1,1,0,"test address",true,1);
        Database.currentLoggedInOrganizer = organizerLevelTwo;
        organizerLevelTwo.addLocation(location);
        organizerLevelTwo.addNonSeatedPlannedEvent(nonSeatedPlannedEvent);
    }

    @Test
    public void testAddLocation(){
        /*Object already been added in init*/
        Assert.assertNotNull(organizerLevelTwo.getLocations().get(0));
    }

    @Test
    public void testGetLocation(){
        Assert.assertNotNull(organizerLevelTwo.getLocations().get(0));
    }

    @Test
    public void testAddNonSeatedPlannedEvent(){
        /*Object already been added in init*/
        Assert.assertNotNull(organizerLevelTwo.getNonSeatedPlannedEvents().get(0));
    }

    @Test
    public void testGetNonSeatedPlannedEvents(){
        Assert.assertNotNull(organizerLevelTwo.getNonSeatedPlannedEvents().get(0));
    }

    @Test
    public void testGetOrganization(){
        Assert.assertNotNull(Database.currentLoggedInOrganizer.getOrganization());
    }

    @Test
    public void testSetOrganization(){
        Database.currentLoggedInOrganizer.setOrganization("test");
        Assert.assertEquals("test",Database.currentLoggedInOrganizer.getOrganization());
    }

    @Test
    public void testGetAccessLevel(){
        Assert.assertEquals(2,organizerLevelTwo.getAccessLevel());
    }

    @Test
    public void testSetAccessLevel(){
        organizerLevelTwo.setAccessLevel(1);
        Assert.assertEquals(1,organizerLevelTwo.getAccessLevel());

    }
}
