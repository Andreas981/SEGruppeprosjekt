package Security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
