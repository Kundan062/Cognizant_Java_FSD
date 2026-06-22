import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(Exercise1_MockingServiceDependencyInControllerTest.UserController.class)
public class Exercise1_MockingServiceDependencyInControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    static class User {
        private final Long id;
        private final String name;

        User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    interface UserService {
        User getUserById(Long id);
    }

    @RestController
    @RequestMapping("/users")
    static class UserController {
        private final UserService userService;

        UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/{id}")
        ResponseEntity<User> getUser(@PathVariable Long id) {
            return ResponseEntity.ok(userService.getUserById(id));
        }
    }

    @Test
    void testControllerWithMockedService() throws Exception {
        when(userService.getUserById(1L)).thenReturn(new User(1L, "Vinay"));

        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Vinay"));
    }
}
