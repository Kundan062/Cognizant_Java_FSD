import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest(classes = Exercise4_IntegrationTestWithSpringBoot.TestApplication.class)
@AutoConfigureMockMvc
public class Exercise4_IntegrationTestWithSpringBoot {
    @Autowired
    private MockMvc mockMvc;

    record User(Long id, String name) {
    }

    @SpringBootApplication
    static class TestApplication {
        public static void main(String[] args) {
            SpringApplication.run(TestApplication.class, args);
        }
    }

    @Repository
    static class UserRepository {
        private final Map<Long, User> users = Map.of(1L, new User(1L, "Vinay"));

        User findById(Long id) {
            return users.get(id);
        }
    }

    @Service
    static class UserService {
        private final UserRepository userRepository;

        UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        User getUserById(Long id) {
            return userRepository.findById(id);
        }
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
    void testFullFlowFromControllerToRepository() throws Exception {
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Vinay"));
    }
}
