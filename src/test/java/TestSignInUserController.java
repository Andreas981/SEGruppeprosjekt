import Dummy.Database;
import Model.Customer;
import Model.Organizer;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSignInUserController {

    Organizer organizer;
    Customer customer;

    @Before
    public void init(){

        organizer = new Organizer("test name","test address","test email","12312312",
                "test username", "test password", new LocalDate(1991,1,1),"test org",2);
        customer = new Customer("test name","test address","test email","12312312",
                "test username", "test password", new LocalDate(1991,1,1));
        Database.currentLoggedInOrganizer = organizer;
        Database.currentLoggedInCustomer = customer;

    }

    @Test
    public void testSignInOrganizer() {
        Assert.assertEquals("test name",Database.currentLoggedInOrganizer.getFirstName());
        Assert.assertEquals("test password",Database.currentLoggedInOrganizer.getPassword());
    }

    @Test
    public void testSignInCustomer() {
        Assert.assertEquals("test name",Database.currentLoggedInCustomer.getFirstName());
        Assert.assertEquals("test password",Database.currentLoggedInCustomer.getPassword());

    }

}
