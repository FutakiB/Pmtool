package pmtool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryUserRepositoryTest {
    private InMemoryUserRepository userRepository = new InMemoryUserRepository();
    static  private ArrayList<User> users = new ArrayList<>();
    @BeforeAll
    static void BeforeAll(){
        for (int i = 0; i < 5; i++) {
            users.add(new User("Name" + 3 * i, User.Role.ANALYST));
            users.add(new User("Name" + 3 * i + 1, User.Role.DEV));
            users.add(new User("Name" + 3 * i + 2, User.Role.MANAGER));
        }
    }
    @BeforeEach
    void BeforeEach() {
        userRepository.db.clear();
        userRepository.db.addAll(users);
    }

    @Test
    void testfindAll() {
        List<User> u = userRepository.findAll();
        assertEquals(userRepository.db.size(), users.size());
        assertTrue(userRepository.db.containsAll(u));
    }

    @Test
    void testfindAllById() {
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(5);
        integers.add(7);
        List<User> u = userRepository.findAllById(integers);
        assertEquals(u.size(),3);
        assertEquals(u.get(0).getId(),3);
        assertEquals(u.get(1).getId(),5);
        assertEquals(u.get(2).getId(),7);

    }

    @Test
    void testcount() {
        assertEquals(userRepository.count(),users.size());
    }

    @Test
    void testdeleteById() {

    }


    @Test
    void delete() {
    }

    @Test
    void deleteAllById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void testDeleteAll() {
    }

    @Test
    void save() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void existsById() {
    }

    @Test
    void flush() {
    }

    @Test
    void getOne() {
    }

    @Test
    void getById() {
    }
}