import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class Exercise1_MockingAndStubbing {
    interface ExternalApi {
        String getData();
    }

    static class MyService {
        private final ExternalApi api;

        MyService(ExternalApi api) {
            this.api = api;
        }

        String fetchData() {
            return api.getData();
        }
    }

    @Test
    void testExternalApi() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);

        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }
}
