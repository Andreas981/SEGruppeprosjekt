import model.PlannedEvent;
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
}
