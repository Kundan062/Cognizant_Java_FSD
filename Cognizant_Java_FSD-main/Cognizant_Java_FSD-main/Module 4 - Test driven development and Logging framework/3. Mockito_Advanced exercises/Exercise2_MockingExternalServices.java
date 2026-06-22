import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class Exercise2_MockingExternalServices {
    interface RestClient {
        String getResponse();
    }

    static class ApiService {
        private final RestClient restClient;

        ApiService(RestClient restClient) {
            this.restClient = restClient;
        }

        String fetchData() {
            return "Fetched " + restClient.getResponse();
        }
    }

    @Test
    void testServiceWithMockRestClient() {
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");
        ApiService apiService = new ApiService(mockRestClient);

        String result = apiService.fetchData();

        assertEquals("Fetched Mock Response", result);
    }
}
