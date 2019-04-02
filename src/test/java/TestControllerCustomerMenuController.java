import Controller.CustomerMenuController;
import Dummy.Database;
import Dummy.InitStart;
import com.sun.org.apache.xml.internal.security.Init;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestControllerCustomerMenuController {
    CustomerMenuController customerMenuController;

    @Before
    public void testCreateController(){
        customerMenuController = new CustomerMenuController();
        InitStart.Init();
    }

    @Test
    public void testEnterInvalidEventnumber(){

    }

    // TODO
    // Method for testing on dummy data

    @Test
    public void testEnterValidEventNumber(){
        //Assert.assertTrue(customerMenuController.validateUserSelection("0013"));
    }
}
