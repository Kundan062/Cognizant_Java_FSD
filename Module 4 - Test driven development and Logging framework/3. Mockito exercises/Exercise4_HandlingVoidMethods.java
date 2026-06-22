import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class Exercise4_HandlingVoidMethods {
    interface AuditLogger {
        void log(String message);
    }

    @Test
    void testVoidMethod() {
        AuditLogger logger = mock(AuditLogger.class);
        doNothing().when(logger).log("saved");

        logger.log("saved");

        verify(logger).log("saved");
    }
}
