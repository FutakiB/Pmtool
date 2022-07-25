package pmtool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    void save_calls_save_in_userRepository_and_returns_saved_entity() {
        User user = new User(1, "User", User.Role.MANAGER);
        when(mockUserRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        verify(mockUserRepository, times(1)).save(user);
        assertEquals(user, result);
    }

//    @Test
//    void save_adds_user_when_id_is_not_set() {
//        User userToBeSaved = new User( "User", User.Role.MANAGER);
//        User userAfterSave = new User( 1,"User", User.Role.MANAGER);
//        Optional<User> emptyUser = Optional.empty();
//        when(mockUserRepository.findById(userToBeSaved.getId())).thenReturn(emptyUser);
//        when(mockUserRepository.save(userToBeSaved)).thenReturn(userAfterSave);
//
//        User result = userService.save(user);
//
//        verify(mockUserRepository, times(1)).save(user);
//        assertEquals(user, result);
//    }

    @Test
    void delete_calls_delete_in_userRepository() {
        User user = new User(1, "User", User.Role.MANAGER);

        userService.delete(user);

        verify(mockUserRepository, times(1)).delete(user);
    }

    @Test
    void delete_calls_deleteById_in_userRepository() {
        userService.delete(1);

        verify(mockUserRepository, times(1)).deleteById(1);
    }
}