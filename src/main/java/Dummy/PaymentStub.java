package Dummy;

public class PaymentStub {
    /*
    PayPal
    Debit
    Pay at desk
     */


    





    private void errorNotEnoughtMoney(){
        System.out.println("Sorry, payment faile due to account balance.");
    }

    private void errorDatabaseConnectionFail(){
        System.out.println("Sorry, payment failed due to server issues.");
    }

    private void errorWrongPinCode(){
        System.out.println("Sorry, payment failed due to wrong pin code");
    }

    private void success(){
        System.out.println("Payment Successfull. Thanks for the order.");
    }

}
