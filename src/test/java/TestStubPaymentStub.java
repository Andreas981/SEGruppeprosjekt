import Dummy.PaymentStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestStubPaymentStub {

    PaymentStub stub;

    @Before
    public void init(){
        stub = new PaymentStub();
    }

    @Test
    public void paymentFromPayPalAccountIsTrue(){
        Assert.assertTrue(stub.payPal("andremi"));
    }

    @Test
    public void paymentForPayPalAccountIsFalse(){
        Assert.assertFalse(stub.payPal("andreni"));
    }

    @Test
    public void paymentForDebitCardisTrue(){
        Assert.assertTrue(stub.debitCard(4444));
    }

    @Test
    public void paymentForDebitCardIsFalse(){
        Assert.assertFalse(stub.debitCard(123));
    }

}
