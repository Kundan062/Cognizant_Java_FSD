import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import org.junit.jupiter.api.Test;

public class Exercise5_TimeoutAndPerformanceTesting {
    static class PerformanceTester {
        void performTask() {
            int total = 0;
            for (int index = 0; index < 1_000; index++) {
                total += index;
            }
        }
    }

    @Test
    void testMethodCompletesWithinTimeLimit() {
        PerformanceTester tester = new PerformanceTester();

        assertTimeout(Duration.ofMillis(100), tester::performTask);
    }
}
