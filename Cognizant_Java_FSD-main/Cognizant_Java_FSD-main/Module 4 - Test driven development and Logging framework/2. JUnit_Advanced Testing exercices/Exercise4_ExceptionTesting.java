import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Exercise4_ExceptionTesting {
    static class ExceptionThrower {
        void throwException() {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    @Test
    void testExpectedException() {
        ExceptionThrower thrower = new ExceptionThrower();

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            thrower::throwException
        );

        assertEquals("Invalid input", exception.getMessage());
    }
}
