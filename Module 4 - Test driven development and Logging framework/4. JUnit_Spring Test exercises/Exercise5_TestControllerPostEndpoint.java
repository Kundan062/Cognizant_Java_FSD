import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(Exercise5_TestControllerPostEndpoint.UserController.class)
public class Exercise5_TestControllerPostEndpoint {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    static class User {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    interface UserService {
        User saveUser(User user);
    }

    @RestController
    @RequestMapping("/users")
    static class UserController {
        private final UserService userService;

        UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping
        ResponseEntity<User> createUser(@RequestBody User user) {
            return ResponseEntity.ok(userService.saveUser(user));
        }
    }

    @Test
    void testCreateUserEndpoint() throws Exception {
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Vinay");
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Vinay\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Vinay"));
    }
}
