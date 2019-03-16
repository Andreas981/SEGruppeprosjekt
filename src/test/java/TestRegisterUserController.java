import Controller.RegisterUserController;
import Model.Customer;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRegisterUserController {
    RegisterUserController registerUserController;
    Customer customer;

    @Before
    public void init() {
        registerUserController = new RegisterUserController();
        customer = new Customer("test name", "test lastname","test mail",
                "test telephone number","test username", "test password",
                new LocalDate(1990,2,2));
    }

    @Test
    public void TestRegisterCustomerIntoDatabase() {
        Assert.assertEquals(true, registerUserController.registerCustomerIntoDatabase(customer.getFirstName(),customer.getLastName(),customer.getMail(),customer.getTelephone(),customer.getUsername(),customer.getPassword(),customer.getBirthday()));
    }
}