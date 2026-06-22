import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public class Exercise2_MockingRepositoryInServiceTest {
    static class User {
        private final Long id;
        private final String name;

        User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    interface UserRepository extends JpaRepository<User, Long> {
    }

    @Service
    static class UserService {
        private final UserRepository userRepository;

        UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        User getUserById(Long id) {
            return userRepository.findById(id).orElse(null);
        }
    }

    @Test
    void testServiceWithMockedRepository() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "Vinay")));
        UserService userService = new UserService(userRepository);

        User user = userService.getUserById(1L);

        assertEquals("Vinay", user.getName());
    }
}
