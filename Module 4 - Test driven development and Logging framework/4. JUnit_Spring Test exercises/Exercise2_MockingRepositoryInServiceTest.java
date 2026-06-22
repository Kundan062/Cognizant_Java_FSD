import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public class Exercise2_MockingRepositoryInServiceTest {
    static class User {
        private Long id;
        private String name;

        User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        Long getId() {
            return id;
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
    void testGetUserById() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "Vinay")));
        UserService userService = new UserService(userRepository);

        User user = userService.getUserById(1L);

        assertNotNull(user);
        assertEquals("Vinay", user.getName());
    }
}
