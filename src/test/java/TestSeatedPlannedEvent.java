import Model.SeatedPlannedEvent;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;

public class TestSeatedPlannedEvent {

    // TODO Test case for viewing sold tickets

    @Test
    public void createAseatedEvent(){
        SeatedPlannedEvent seatedPlannedEvent = new SeatedPlannedEvent("TestEvent",new Date(2019,4,4),new Time(22,2,22),90,18);
        Assert.assertEquals(seatedPlannedEvent,seatedPlannedEvent);
    }
}
