package Security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    public static Boolean regEx(String pattern, String input){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
