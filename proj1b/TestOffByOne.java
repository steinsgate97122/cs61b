import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        Assert.assertTrue(offByOne.equalChars('a', 'b'));
        Assert.assertTrue(offByOne.equalChars('c', 'b'));
        Assert.assertTrue(offByOne.equalChars('%', '&'));
        Assert.assertFalse(offByOne.equalChars('a', 'a'));
        Assert.assertFalse(offByOne.equalChars('a', 'z'));
        Assert.assertFalse(offByOne.equalChars('a', 'B'));
    }
}
