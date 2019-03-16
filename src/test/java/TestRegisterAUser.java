import Controller.RegisterUserController;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRegisterAUser {
    RegisterUserController registerUserController;

    //Variabel for customer

    @Before
    public void init(){
        registerUserController = new RegisterUserController();
        //Instansier customer
    }

    @Test
    public void testRegisterUsername(){
        //Bruk ny customer her
        Assert.assertTrue(registerUserController.checkUsername("persen"));
    }

    @Test
    public void testEnterblankUsername(){
        Assert.assertFalse(registerUserController.checkUsername(""));
    }

    @Test
    public void testEnteringInvalidDate(){
        Assert.assertNull(null,registerUserController.checkDateEntered("00-00-00"));
    }

    @Test
    public void testEnterAvalidDate(){
        Assert.assertEquals(new LocalDate(2000,2,2),registerUserController.checkDateEntered("2000-02-02"));
    }

    // TODO Init test

}

