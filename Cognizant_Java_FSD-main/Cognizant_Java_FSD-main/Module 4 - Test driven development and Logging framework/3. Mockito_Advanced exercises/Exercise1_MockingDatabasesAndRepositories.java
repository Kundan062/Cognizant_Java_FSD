import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class Exercise1_MockingDatabasesAndRepositories {
    interface Repository {
        String getData();
    }

    static class Service {
        private final Repository repository;

        Service(Repository repository) {
            this.repository = repository;
        }

        String processData() {
            return "Processed " + repository.getData();
        }
    }

    @Test
    void testServiceWithMockRepository() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");
        Service service = new Service(mockRepository);

        String result = service.processData();

        assertEquals("Processed Mock Data", result);
    }
}
