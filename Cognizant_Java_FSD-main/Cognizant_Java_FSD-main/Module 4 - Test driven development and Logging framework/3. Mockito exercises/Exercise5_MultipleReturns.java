import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class Exercise5_MultipleReturns {
    interface ExternalApi {
        String getData();
    }

    @Test
    void testMultipleReturnValues() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("First Data", "Second Data");

        assertEquals("First Data", mockApi.getData());
        assertEquals("Second Data", mockApi.getData());
    }
}
