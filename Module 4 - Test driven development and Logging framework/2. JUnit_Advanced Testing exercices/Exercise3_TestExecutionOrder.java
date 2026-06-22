import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Exercise3_TestExecutionOrder {
    private static final StringBuilder executionLog = new StringBuilder();

    @Test
    @Order(1)
    void firstTest() {
        executionLog.append("A");
        assertEquals("A", executionLog.toString());
    }

    @Test
    @Order(2)
    void secondTest() {
        executionLog.append("B");
        assertEquals("AB", executionLog.toString());
    }
}
