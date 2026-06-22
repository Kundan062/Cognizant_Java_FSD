import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Exercise1_SettingUpJUnit {
    static class Calculator {
        int add(int first, int second) {
            return first + second;
        }
    }

    @Test
    public void testJUnitSetupWithSimpleAssertion() {
        Calculator calculator = new Calculator();

        int result = calculator.add(2, 3);

        assertEquals(5, result);
    }
}
