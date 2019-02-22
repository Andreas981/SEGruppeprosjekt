import Model.PlannedEvent;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;





public class TestEventCreation {

    @Test
    public void objectInstanceCreation(){
        PlannedEvent plannedEvent = new PlannedEvent("TestEvent",new Date(2019,4,4),new Time(22,2,22),90,18 );
        Assert.assertEquals(plannedEvent,plannedEvent);
    }

    // TODO Make a test that asserts fail when trying to make an object from the abstract class

    // TODO Test for ticketbuying

    // TODO Test
}
