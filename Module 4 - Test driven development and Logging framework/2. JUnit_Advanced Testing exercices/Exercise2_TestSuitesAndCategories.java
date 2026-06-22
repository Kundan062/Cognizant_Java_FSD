import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    Exercise2_TestSuitesAndCategories.CalculatorTest.class,
    Exercise2_TestSuitesAndCategories.StringTest.class
})
public class Exercise2_TestSuitesAndCategories {
    static class CalculatorTest {
        @Test
        void additionWorks() {
            assertEquals(4, 2 + 2);
        }
    }

    static class StringTest {
        @Test
        void stringStartsWithPrefix() {
            assertTrue("JUnit".startsWith("J"));
        }
    }
}
