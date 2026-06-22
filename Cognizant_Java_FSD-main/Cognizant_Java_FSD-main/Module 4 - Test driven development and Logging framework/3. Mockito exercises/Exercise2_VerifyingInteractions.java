import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class Exercise2_VerifyingInteractions {
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
    void testVerifyInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
    }
}
