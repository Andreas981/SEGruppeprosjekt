import Controller.RegisterUserController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRegisterAUser {
    @Before
    public void init(){
        RegisterUserController registerUserController = new RegisterUserController();
    }

    @Test
    public void userNameIsValid(){
        Assert.assertEquals(true,"persen");
    }


}

