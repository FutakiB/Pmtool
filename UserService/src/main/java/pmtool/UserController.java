package pmtool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pmtool.User;
import pmtool.UserManagement;
import pmtool.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserManagement userService;

    @Autowired
    public UserController(UserManagement userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getAll();
    }
}
