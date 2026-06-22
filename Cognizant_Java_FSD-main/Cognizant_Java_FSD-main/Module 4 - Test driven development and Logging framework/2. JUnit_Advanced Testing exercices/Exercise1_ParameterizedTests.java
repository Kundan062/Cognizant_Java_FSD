import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Exercise1_ParameterizedTests {
    static class EvenChecker {
        boolean isEven(int number) {
            return number % 2 == 0;
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 10, 100})
    void testEvenNumbers(int number) {
        EvenChecker checker = new EvenChecker();

        assertTrue(checker.isEven(number));
    }
}
