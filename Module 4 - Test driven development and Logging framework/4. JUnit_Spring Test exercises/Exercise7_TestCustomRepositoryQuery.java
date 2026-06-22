import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public class Exercise7_TestCustomRepositoryQuery {
    static class User {
        private final String name;

        User(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByName(String name);
    }

    @Test
    void testFindByNameCustomQueryMethod() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByName("Vinay")).thenReturn(List.of(new User("Vinay")));

        List<User> users = userRepository.findByName("Vinay");

        assertEquals(1, users.size());
        assertEquals("Vinay", users.get(0).getName());
    }
}
