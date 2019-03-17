package Dummy;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PaymentStub {
    /*
    PayPal
    Debit
    Pay at desk
     */

    public Boolean payPal(String username){
        String hashed;

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(username.getBytes());

            byte[] digest = md.digest();

            BigInteger bigInt = new BigInteger(1, digest);

            hashed = bigInt.toString(16);

        }catch (NoSuchAlgorithmException e){
            errorDatabaseConnectionFail();
            return false;
        }

        String[] numbers = hashed.replaceAll("\\D+","").split("");
        int sum = 0;
        for(int i = 0; i < numbers.length; i++)
            if(numbers[i].equals("1") || numbers[i].equals("3"))
                sum ++;

        if(sum % 3 == 0) {
            errorWrongPinCode();
            return false;
        }else if(sum % 4 == 0){
            errorDatabaseConnectionFail();
            return false;
        }else{
            success();
            return true;
        }
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
