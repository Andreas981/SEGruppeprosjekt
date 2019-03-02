package Security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassHash {
    public static String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(password.getBytes());

            byte[] digest = md.digest();

            BigInteger bigInt = new BigInteger(1, digest);

            return bigInt.toString(16);

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return null;
    }

}
