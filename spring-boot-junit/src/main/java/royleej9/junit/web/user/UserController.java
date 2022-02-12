package royleej9.junit.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

    public UserController() {
        log.info("INIT UserController============================================");
    }

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.getUsers(null);
    }

    @GetMapping(value = "/users/{userId}")
    public List<User> getUsersById(@PathVariable String userId) {
        return userService.getUsers(User.builder().id(userId).build());
    }

    @PostMapping(value = "/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.insertUser(user);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
