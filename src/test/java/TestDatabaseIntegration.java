import Controller.SeatedEventController;
import Dummy.Database;
import Dummy.InitStart;
import Model.Organizer;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDatabaseIntegration {

    Organizer organizer;

    @Before
    public void inti(){
        organizer = new Organizer("Mats", "Hansen", "mail", "12312312", "mats", "abc123", new LocalDate(2000, 12, 12), "Sommerkroppen", 1);
        Database.currentLoggedInOrganizer = organizer;
        InitStart.Init();
    }

    @Test
    public void LoosingDatabaseBeforeAddingLocation(){
        SeatedEventController sec = new SeatedEventController();

        sec.addSeatedEvent(0, 0, "Event", "1998-12-12", "12-12", 18, 123, 12);
        Database.currentLoggedInOrganizer = null;

        //Assert.assert("Event", Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getNameOfEvent());
    }

}
