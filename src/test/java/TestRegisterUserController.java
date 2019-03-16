import Dummy.Database;
import Model.Customer;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRegisterUserController {

    Customer customer;

    @Before
    public void createUser() {
        Database.customers.add(new Customer("Per","Sandberg","per@mail.no",
                "12312312","PerS","per123",new LocalDate(1990,6,1)));
    }

    @Test
    public void TestRegisterCustomerIntoDatabase() {
        Assert.assertEquals("Per", Database.currentLoggedInCustomer.getFirstName());
    }




}
