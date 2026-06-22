import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class Exercise3_ArgumentMatching {
    interface NotificationSender {
        void send(String userId, String message);
    }

    static class NotificationService {
        private final NotificationSender sender;

        NotificationService(NotificationSender sender) {
            this.sender = sender;
        }

        void welcomeUser(String userId) {
            sender.send(userId, "Welcome");
        }
    }

    @Test
    void testArgumentMatching() {
        NotificationSender sender = mock(NotificationSender.class);
        NotificationService service = new NotificationService(sender);

        service.welcomeUser("user-101");

        verify(sender).send(eq("user-101"), eq("Welcome"));
    }
}
