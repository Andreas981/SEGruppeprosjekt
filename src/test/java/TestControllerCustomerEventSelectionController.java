import Controller.CustomerEventSelectionController;
import Dummy.InitStart;
import org.junit.Before;
import org.junit.Test;

public class TestControllerCustomerEventSelectionController {
    CustomerEventSelectionController customerEventSelectionController;

    @Before
    public void createController(){
        customerEventSelectionController = new CustomerEventSelectionController();
        InitStart.Init();
    }

    @Test
    public void enterInvalidEventnumber(){

    }

    // TODO
    // Method for testing on dummy data

    @Test
    public void enterValidEventNumber(){
        //Assert.assertTrue(customerEventSelectionController.validateUserSelection("0013"));
    }
}
