import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public class Exercise6_TestServiceExceptionHandling {
    static class User {
    }

    interface UserRepository extends JpaRepository<User, Long> {
    }

    @Service
    static class UserService {
        private final UserRepository userRepository;

        UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        User getRequiredUserById(Long id) {
            return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        }
    }

    @Test
    void testMissingUserThrowsException() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);

        assertThrows(NoSuchElementException.class, () -> userService.getRequiredUserById(99L));
    }
}
