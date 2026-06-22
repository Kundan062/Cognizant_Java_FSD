import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class Exercise4_MockingNetworkInteractions {
    interface NetworkClient {
        String connect();
    }

    static class NetworkService {
        private final NetworkClient networkClient;

        NetworkService(NetworkClient networkClient) {
            this.networkClient = networkClient;
        }

        String connectToServer() {
            return "Connected to " + networkClient.connect();
        }
    }

    @Test
    void testServiceWithMockNetworkClient() {
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");
        NetworkService networkService = new NetworkService(mockNetworkClient);

        String result = networkService.connectToServer();

        assertEquals("Connected to Mock Connection", result);
    }
}
