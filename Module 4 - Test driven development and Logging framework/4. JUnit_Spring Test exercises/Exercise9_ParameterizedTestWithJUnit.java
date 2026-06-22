import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.stereotype.Service;

public class Exercise9_ParameterizedTestWithJUnit {
    @Service
    static class CalculatorService {
        int add(int a, int b) {
            return a + b;
        }
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "10, 5, 15",
        "-2, 8, 6"
    })
    void testAddWithMultipleInputs(int first, int second, int expected) {
        CalculatorService calculatorService = new CalculatorService();

        assertEquals(expected, calculatorService.add(first, second));
    }
}
