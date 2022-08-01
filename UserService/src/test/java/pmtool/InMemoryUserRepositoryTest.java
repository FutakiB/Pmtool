package pmtool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {
//    private InMemoryUserRepository userRepository = new InMemoryUserRepository();
//    static  private ArrayList<User> users = new ArrayList<>();
//    @BeforeAll
//    static void BeforeAll(){
//        for (int i = 0; i < 5; i++) {
//            users.add(new User("Name" + 3 * i, User.Role.ANALYST));
//            users.add(new User("Name" + 3 * i + 1, User.Role.DEV));
//            users.add(new User("Name" + 3 * i + 2, User.Role.MANAGER));
//        }
//    }
//    @BeforeEach
//    void BeforeEach() {
//        userRepository.db.clear();
//        userRepository.db.addAll(users);
//    }
//
//    @Test
//    void testfindAll() {
//        List<User> u = userRepository.findAll();
//        assertEquals(userRepository.db.size(), users.size());
//        assertTrue(userRepository.db.containsAll(u));
//    }
//
//    @Test
//    void testfindAllById() {
//        List<Integer> integers = new ArrayList<>();
//        integers.add(3);
//        integers.add(5);
//        integers.add(7);
//        List<User> u = userRepository.findAllById(integers);
//        assertEquals(u.size(),3);
//
//        for (User us:u) {
//            assertTrue(integers.contains(us.getId()));
//        }
//
//
//    }
//
//    @Test
//    void testCount() {
//        assertEquals(userRepository.count(),users.size());
//    }
//
//    @Test
//    void testDeleteById() {
//        userRepository.deleteById(11);
//        assertFalse(userRepository.db.stream().anyMatch((user) -> user.getId().equals(11)));
//    }
//
//
//    @Test
//    void testDeleteEntity() {
//        User u = users.get(7);
//        userRepository.delete(u);
//        assertFalse(userRepository.db.contains(u));
//    }
//
//    @Test
//    void testDeleteAllById() {
//        ArrayList<Integer> id = new ArrayList<>();
//        id.add(7);
//        id.add(11);
//        id.add(12);
//        userRepository.deleteAllById(id);
//        assertFalse(userRepository.db.stream().anyMatch((user) -> user.getId().equals(11)));
//        assertFalse(userRepository.db.stream().anyMatch((user) -> user.getId().equals(12)));
//        assertFalse(userRepository.db.stream().anyMatch((user) -> user.getId().equals(7)));
//
//
//    }
//
//    @Test
//    void testDeleteAll() {
//        userRepository.deleteAll();
//        assertTrue(userRepository.db.isEmpty());
//    }
//
//    @Test
//    void save() {
//        User u = new User("New User 007", User.Role.MANAGER);
//        User result = userRepository.save(u);
//        assertEquals(result,u);
//        assertTrue(userRepository.db.contains(u));
//    }
//
//    @Test
//    void saveAll() {
//        User u1 = new User("New User 007", User.Role.MANAGER);
//        User u2 = new User("New User 123", User.Role.DEV);
//        User u3 = new User("New User ABC", User.Role.ANALYST);
//        List<User> u = new ArrayList<>();
//        u.add(u1);
//        u.add(u2);
//        u.add(u3);
//        List<User> result = userRepository.saveAll(u);
//        assertEquals(result,u);
//        assertTrue(userRepository.db.containsAll(u));
//    }
//
//    @Test
//    void testFindById() {
//        Optional<User> u = userRepository.findById(5);
//        assertNotEquals(u,Optional.empty());
//        Optional<User> uu = userRepository.findById(50);
//        assertEquals(uu,Optional.empty());
//    }
//
//    @Test
//    void testExistsById() {
//        assertTrue(userRepository.existsById(7));
//        assertFalse(userRepository.existsById(70));
//
//    }
//
//    @Test
//    void testFlush() {
//        userRepository.flush();
//        assertTrue(userRepository.db.isEmpty());
//    }


}