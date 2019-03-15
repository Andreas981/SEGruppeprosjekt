import Controller.CustomerMenuController;
import Dummy.Database;
import Dummy.InitStart;
import com.sun.org.apache.xml.internal.security.Init;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCustomerMenuController {
    CustomerMenuController customerMenuController;

    @Before
    public void createController(){
        customerMenuController = new CustomerMenuController();
        InitStart.Init();
    }

    @Test
    public void enterInvalidEventnumber(){
        Assert.assertFalse(customerMenuController.validateUserSelection("aaaa"));
    }

    // TODO
    // Method for testing on dummy data

    @Test
    public void enterValidEventNumber(){
        //Assert.assertTrue(customerMenuController.validateUserSelection("0013"));
    }
}
