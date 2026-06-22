import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(Exercise8_TestControllerExceptionHandling.UserController.class)
@Import(Exercise8_TestControllerExceptionHandling.GlobalExceptionHandler.class)
public class Exercise8_TestControllerExceptionHandling {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    interface UserService {
        String getUserById(Long id);
    }

    @RestController
    @RequestMapping("/users")
    static class UserController {
        private final UserService userService;

        UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/{id}")
        String getUser(@PathVariable Long id) {
            return userService.getUserById(id);
        }
    }

    @ControllerAdvice
    static class GlobalExceptionHandler {
        @ExceptionHandler(NoSuchElementException.class)
        ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @Test
    void testControllerAdviceReturnsNotFound() throws Exception {
        when(userService.getUserById(99L)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/users/99"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("User not found"));
    }
}
