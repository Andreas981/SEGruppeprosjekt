package Dummy;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PaymentStub {
    /*
        Instructions:

        Pay pal:
            If the amount of the numbers "1" and "3" in the MD5 hashed string is
            dividable by 3 or 4 it will return false. Else it will return true
        Debit card:
            If the card numbers length is less than 4 or
            first four numbers are dividable by 3 or 5 it's false,
            if it's dividable by 2 after 3 and 5 check then it's true.
            Else it will return false
        Vipps:
            If the telephone numbers length is less than 5 or starts with 1,2,3 or 4 it's false
            Else it's true
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

    public Boolean debitCard(int cardNumber){
        String[] cardNumbers = String.valueOf(cardNumber).split("");

        if(cardNumbers.length < 4){
            errorCantFindCard();
            return false;
        }

        int fourFirst = 0;
        for(int i = 0; i < 4; i++){
            fourFirst += Integer.parseInt(cardNumbers[i]);
        }

        if(fourFirst % 3 == 0 || fourFirst % 5 == 0){
            errorCantFindCard();
            return false;
        }else if(fourFirst % 2 == 0){
            success();
            return true;
        }

        errorWrongPinCode();
        return false;
    }

    public Boolean vipps(int tlfNummer){

        String[] number = String.valueOf(tlfNummer).split("");

        if(number.length < 5){
            errorWrongPinCode();
            return false;
        }

        if(number[0].equals("1") || number[0].equals("2") || number[0].equals("3") || number[0].equals("4")){
            errorDatabaseConnectionFail();
            return false;
        }else{
            success();
            return true;
        }
    }

    private void errorDatabaseConnectionFail(){
        System.err.println("Sorry, payment failed due to server issues.\n" +
                "Please try again");
    }
    private void errorWrongPinCode(){
        System.err.println("Sorry, payment failed due to wrong pin code");
    }
    private void errorCantFindCard(){
        System.err.println("Sorry, could not find your card");
    }
    private void success(){
        System.out.println("Payment Successfull. Thanks for the order.");
    }

}
