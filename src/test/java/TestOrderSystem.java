import Controller.OrderController;
import org.junit.Before;

public class TestOrderSystem {

    OrderController orderController;

    @Before
    public void init(){
        orderController = new OrderController();
    }
}
