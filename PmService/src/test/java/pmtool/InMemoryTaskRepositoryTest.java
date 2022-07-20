package pmtool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InMemoryTaskRepositoryTest {
    static InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();
    static List<Task> testData = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        testData.add(new Task(0,"Task 1", Duration.ofMinutes(180)));
        testData.add(new Task(1,"Task 2", Duration.ofMinutes(60)));
        testData.add(new Task(2,"Task 3", Duration.ofMinutes(300)));
    }

    @BeforeEach
    void beforeEach() {
        taskRepository.deleteAll();
        taskRepository.saveAll(testData);
    }

    @Test
    void findAll_returns_all_entities() {
        List<Task> result= taskRepository.findAll();

        assertEquals(testData.size(),result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testData.get(i),result.get(i));
        }
    }

    @Test
    void findAllById_returns_all_entities_with_given_ids() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(0);
        ids.add(1);
        ids.add(2);

        List<Task> result= taskRepository.findAllById(ids);

        assertEquals(testData.size(),result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(ids.get(i), result.get(i).getId());
            assertEquals(testData.get(i),result.get(i));
        }
    }

    @Test
    void count_returns_correct_value() {
        long result= taskRepository.count();

        assertEquals(testData.size(),result);
    }

    @Test
    void deleteById_removes_entity_with_given_id() {
        taskRepository.deleteById(1);

        assertEquals(testData.size()-1, taskRepository.count());
        assertFalse(taskRepository.findById(1).isPresent());
        assertEquals(testData.get(0), taskRepository.findById(0).orElse(null));
        assertEquals(testData.get(2), taskRepository.findById(2).orElse(null));
    }

    @Test
    void delete_removes_given_entity() {
        taskRepository.delete(testData.get(1));

        assertEquals(testData.size()-1, taskRepository.count());
        assertFalse(taskRepository.findById(1).isPresent());
        assertEquals(testData.get(0), taskRepository.findById(0).orElse(null));
        assertEquals(testData.get(2), taskRepository.findById(2).orElse(null));
    }

    @Test
    void deleteAllById_removes_entities_with_given_ids() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        taskRepository.deleteAllById(ids);

        assertEquals(testData.size()-ids.size(), taskRepository.count());
        assertFalse(taskRepository.findById(1).isPresent());
        assertFalse(taskRepository.findById(2).isPresent());
        assertEquals(testData.get(0), taskRepository.findById(0).orElse(null));
    }

    @Test
    void deleteAll_removes_all_given_entities() {
        ArrayList<Task> toDelete = new ArrayList<>();
        toDelete.add(testData.get(1));
        toDelete.add(testData.get(2));

        taskRepository.deleteAll(toDelete);

        assertEquals(testData.size()-toDelete.size(), taskRepository.count());
        assertEquals(testData.get(0), taskRepository.findById(0).orElse(null));
        assertFalse(taskRepository.findById(1).isPresent());
        assertFalse(taskRepository.findById(2).isPresent());
    }

    @Test
    void deleteAll() {
        taskRepository.deleteAll();
        assertEquals(0, taskRepository.count());
    }

    @Test
    void save_adds_entity_when_id_is_not_present() {
        Task toBeSaved=new Task(1234,"New Task",Duration.ofDays(10));

        long countBefore= taskRepository.count();
        taskRepository.save(toBeSaved);
        long countAfter= taskRepository.count();

        assertEquals(countBefore+1,countAfter);
        assertEquals(toBeSaved, taskRepository.findById(toBeSaved.getId()).orElse(null));
    }

    @Test
    void save_updates_entity_when_id_is_present() {
        Task toBeSaved=new Task(1,"Updated Task",Duration.ofMinutes(31));

        long countBefore= taskRepository.count();
        taskRepository.save(toBeSaved);
        long countAfter= taskRepository.count();

        assertEquals(countBefore,countAfter);
        assertEquals(toBeSaved, taskRepository.findById(toBeSaved.getId()).orElse(null));
    }

    @Test
    void save_adds_entities_when_id_is_not_present() {
        Task m1=new Task(13453,"Updated Task1",Duration.ofMinutes(300));
        Task m2=new Task(12345,"Updated Task2",Duration.ofMinutes(300));
        Task m3=new Task(13345,"Updated Task3",Duration.ofMinutes(300));
        List<Task> toBeSaved=new ArrayList<>();
        toBeSaved.add(m1);
        toBeSaved.add(m2);
        toBeSaved.add(m3);

        long countBefore= taskRepository.count();
        taskRepository.saveAll(toBeSaved);
        long countAfter= taskRepository.count();

        assertEquals(countBefore + toBeSaved.size(),countAfter);
        for (int i = 0; i < toBeSaved.size(); i++) {
            assertEquals(toBeSaved.get(i), taskRepository.findById(toBeSaved.get(i).getId()).orElse(null));
        }
    }

    @Test
    void saveAll_updates_entity_when_id_is_present() {
        Task m1=new Task(0,"Updated Task1",Duration.ofMinutes(300));
        Task m2=new Task(1,"Updated Task2",Duration.ofMinutes(300));
        Task m3=new Task(2,"Updated Task3",Duration.ofMinutes(300));
        List<Task> toBeSaved=new ArrayList<>();
        toBeSaved.add(m1);
        toBeSaved.add(m2);
        toBeSaved.add(m3);

        long countBefore= taskRepository.count();
        taskRepository.saveAll(toBeSaved);
        long countAfter= taskRepository.count();

        assertEquals(countBefore,countAfter);
        for (int i = 0; i < toBeSaved.size(); i++) {
            assertEquals(toBeSaved.get(i), taskRepository.findById(toBeSaved.get(i).getId()).orElse(null));
        }
    }

    @Test
    void findById() {
        assertEquals(0, taskRepository.getById(0).getId());
        assertEquals(testData.get(0), taskRepository.findById(0).orElse(null));
    }

    @Test
    void existsById() {
        assertTrue(taskRepository.existsById(1));
    }

    @Test
    void getById() {
        assertEquals(0, taskRepository.getById(0).getId());
        assertEquals(testData.get(0), taskRepository.getById(0));
    }

    @Test
    void flush() {
        taskRepository.flush();
        assertEquals(0, taskRepository.count());
    }
}
