import Model.SeatedPlannedEvent;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;

public class TestSeatedPlannedEvent {

    // TODO Test case for viewing sold tickets

    @Test
    public void createAseatedEvent(){
        SeatedPlannedEvent seatedPlannedEvent = new SeatedPlannedEvent("Example seated event",new LocalDateTime(2019,3,2,22,00),200,20,0,0, 100);
        Assert.assertEquals(seatedPlannedEvent,seatedPlannedEvent);
    }
}
