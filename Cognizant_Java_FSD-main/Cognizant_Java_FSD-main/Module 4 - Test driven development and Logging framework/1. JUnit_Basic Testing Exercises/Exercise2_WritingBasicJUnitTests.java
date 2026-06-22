import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Exercise2_WritingBasicJUnitTests {
    static class StringHelper {
        String reverse(String text) {
            return new StringBuilder(text).reverse().toString();
        }

        boolean isPalindrome(String text) {
            return text.equals(reverse(text));
        }
    }

    @Test
    public void testReverse() {
        StringHelper helper = new StringHelper();

        String result = helper.reverse("JUnit");

        assertEquals("tinUJ", result);
    }

    @Test
    public void testPalindrome() {
        StringHelper helper = new StringHelper();

        assertTrue(helper.isPalindrome("madam"));
    }
}
