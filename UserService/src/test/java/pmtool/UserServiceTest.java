package pmtool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserService userService;
    InMemoryUserRepository mockUserRepository = mock(InMemoryUserRepository.class);

    @BeforeEach
    void setUp() {
        userService = new UserService(mockUserRepository);
    }

    @Test
    void getAll_returns_all_entities() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "User1", User.Role.DEV));
        users.add(new User(2, "User2", User.Role.MANAGER));
        users.add(new User(3, "User3", User.Role.ANALYST));
        when(mockUserRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAll();

        assertEquals(users, result);
    }

    @Test
    void get_returns_entity_with_given_id() {
        Optional<User> user = Optional.of(new User(1, "User1", User.Role.MANAGER));
        when(mockUserRepository.findById(1)).thenReturn(user);

        Optional<User> result = userService.get(1);

        assertEquals(user, result);
    }

    @Test
    void getUsersByName_returns_all_users_with_given_name() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", User.Role.DEV));
        users.add(new User(2, "John Doe", User.Role.DEV));
        users.add(new User(3, "User3", User.Role.MANAGER));
        users.add(new User(4, "User4", User.Role.ANALYST));
        when(mockUserRepository.findAll()).thenReturn(users);

        List<User> result = userService.getUsersByName("John Doe");

        assertEquals(users.subList(0, 2), result);
    }

    @Test
    void getUsersByRole_returns_all_users_with_given_name() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "User1", User.Role.DEV));
        users.add(new User(2, "User2", User.Role.DEV));
        users.add(new User(3, "User3", User.Role.MANAGER));
        users.add(new User(4, "User4", User.Role.ANALYST));
        when(mockUserRepository.findAll()).thenReturn(users);

        List<User> result = userService.getUsersByRole(User.Role.DEV);

        assertEquals(users.subList(0, 2), result);
    }

    @Test
    void save_updates_entity_when_id_is_present() {
    }

    @Test
    void save_adds_entities_when_id_is_not_present() {
    }

    @Test
    void delete_removes_given_entity() {
    }

    @Test
    void delete_removes_entity_with_given_id() {
    }
}