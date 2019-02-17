import org.junit.Assert;
import org.junit.Test;

public class testCaseStrings {
    @Test
    public void testAstring(){
        Assert.assertEquals("Left",Main.aString("Something"));
    }
    @Test
    public void testCalc(){
        Assert.assertEquals(5, Main.calc(2,3));
    }
}
