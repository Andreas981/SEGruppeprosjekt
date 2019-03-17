import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import Model.Organizer;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNonSeatedEventController {

    Organizer organizer;
    NonSeatedPlannedEvent nonSeatedPlannedEvent;

    @Before
    public void init() {
        organizer = new Organizer("test name","test address","test email","12312312",
                "test username", "test password", new LocalDate(1991,1,1),
                "test org",2);
        nonSeatedPlannedEvent = new NonSeatedPlannedEvent("test name",
                new LocalDateTime(1,2,3,4,5),1, 18,2,
                "test address",true);
        Database.currentLoggedInOrganizer = organizer;
        Database.currentLoggedInOrganizer.addNonSeatedPlannedEvent(nonSeatedPlannedEvent);
    }

    @Test
    public void testAddNonSeatedEvent() {
        Assert.assertEquals("test name", Database.currentLoggedInOrganizer.getNonSeatedPlannedEvents().get(0).getNameOfEvent());
    }

}
