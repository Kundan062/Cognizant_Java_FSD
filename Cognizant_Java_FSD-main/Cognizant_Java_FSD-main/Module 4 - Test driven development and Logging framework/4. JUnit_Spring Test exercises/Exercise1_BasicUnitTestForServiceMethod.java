import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

public class Exercise1_BasicUnitTestForServiceMethod {
    @Service
    static class CalculatorService {
        int add(int a, int b) {
            return a + b;
        }
    }

    @Test
    void testAdd() {
        CalculatorService calculatorService = new CalculatorService();

        int result = calculatorService.add(10, 5);

        assertEquals(15, result);
    }
}
