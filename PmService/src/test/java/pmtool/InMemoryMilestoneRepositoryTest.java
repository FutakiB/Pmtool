package pmtool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pmtool.InMemoryProjectRepositoryTest.projects;

public class InMemoryMilestoneRepositoryTest {
    static InMemoryMilestoneRepository milestones = new InMemoryMilestoneRepository();
    static List<Milestone> testData = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        testData.add(new Milestone(0,"Milestone 1",  LocalDateTime.now(), Duration.ofDays(9)));
        testData.add(new Milestone(1,"Milestone 2",  LocalDateTime.now(), Duration.ofDays(7)));
        testData.add(new Milestone(2,"Milestone 3",  LocalDateTime.now(), Duration.ofDays(9)));
    }

    @BeforeEach
    void beforeEach() {
        milestones.deleteAll();
        milestones.saveAll(testData);
    }

    @Test
    void findAll_returns_all_entities() {
        List<Milestone> result=milestones.findAll();

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

        List<Milestone> result=milestones.findAllById(ids);

        assertEquals(testData.size(),result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(ids.get(i), result.get(i).getId());
            assertEquals(testData.get(i),result.get(i));
        }
    }

    @Test
    void count_returns_correct_value() {
        long result=milestones.count();

        assertEquals(testData.size(),result);
    }

    @Test
    void deleteById_removes_entity_with_given_id() {
        milestones.deleteById(1);

        assertEquals(testData.size()-1,milestones.count());
        assertFalse(milestones.findById(1).isPresent());
        assertEquals(testData.get(0),milestones.findById(0).orElse(null));
        assertEquals(testData.get(2),milestones.findById(2).orElse(null));
    }

    @Test
    void delete_removes_given_entity() {
        milestones.delete(testData.get(1));

        assertEquals(testData.size()-1,milestones.count());
        assertFalse(milestones.findById(1).isPresent());
        assertEquals(testData.get(0),milestones.findById(0).orElse(null));
        assertEquals(testData.get(2),milestones.findById(2).orElse(null));
    }

    @Test
    void deleteAllById_removes_entities_with_given_ids() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        milestones.deleteAllById(ids);

        assertEquals(testData.size()-ids.size(),milestones.count());
        assertFalse(milestones.findById(1).isPresent());
        assertFalse(milestones.findById(2).isPresent());
        assertEquals(testData.get(0),milestones.findById(0).orElse(null));
    }

    @Test
    void deleteAll_removes_all_given_entities() {
        ArrayList<Milestone> toDelete = new ArrayList<>();
        toDelete.add(testData.get(1));
        toDelete.add(testData.get(2));

        milestones.deleteAll(toDelete);

        assertEquals(testData.size()-toDelete.size(),milestones.count());
        assertEquals(testData.get(0),milestones.findById(0).orElse(null));
        assertFalse(milestones.findById(1).isPresent());
        assertFalse(milestones.findById(2).isPresent());
    }

    @Test
    void deleteAll() {
        milestones.deleteAll();
        assertEquals(0, milestones.count());
    }

    @Test
    void save_adds_entity_when_id_is_not_present() {
        Milestone toBeSaved=new Milestone(1234,"New milestone",LocalDateTime.now(),Duration.ofDays(10));

        long countBefore=milestones.count();
        milestones.save(toBeSaved);
        long countAfter=milestones.count();

        assertEquals(countBefore+1,countAfter);
        assertEquals(toBeSaved,milestones.findById(toBeSaved.getId()).orElse(null));
    }

    @Test
    void save_updates_entity_when_id_is_present() {
        Milestone toBeSaved=new Milestone(1,"Updated milestone",LocalDateTime.now(),Duration.ofDays(31));

        long countBefore=milestones.count();
        milestones.save(toBeSaved);
        long countAfter=milestones.count();

        assertEquals(countBefore,countAfter);
        assertEquals(toBeSaved,milestones.findById(toBeSaved.getId()).orElse(null));

    }

    @Test
    void save_adds_entities_when_id_is_not_present() {
        Milestone m1=new Milestone(13453,"Updated milestone1",LocalDateTime.now(),Duration.ofDays(31));
        Milestone m2=new Milestone(12345,"Updated milestone2",LocalDateTime.now(),Duration.ofDays(31));
        Milestone m3=new Milestone(13345,"Updated milestone3",LocalDateTime.now(),Duration.ofDays(31));
        List<Milestone> toBeSaved=new ArrayList<>();
        toBeSaved.add(m1);
        toBeSaved.add(m2);
        toBeSaved.add(m3);

        long countBefore=milestones.count();
        milestones.saveAll(toBeSaved);
        long countAfter=milestones.count();

        assertEquals(countBefore + toBeSaved.size(),countAfter);
        for (int i = 0; i < toBeSaved.size(); i++) {
            assertEquals(toBeSaved.get(i),milestones.findById(toBeSaved.get(i).getId()).orElse(null));
        }
    }

    @Test
    void saveAll_updates_entity_when_id_is_present() {
        Milestone m1=new Milestone(0,"Updated milestone1",LocalDateTime.now(),Duration.ofDays(31));
        Milestone m2=new Milestone(1,"Updated milestone2",LocalDateTime.now(),Duration.ofDays(31));
        Milestone m3=new Milestone(2,"Updated milestone3",LocalDateTime.now(),Duration.ofDays(31));
        List<Milestone> toBeSaved=new ArrayList<>();
        toBeSaved.add(m1);
        toBeSaved.add(m2);
        toBeSaved.add(m3);

        long countBefore=milestones.count();
        milestones.saveAll(toBeSaved);
        long countAfter=milestones.count();

        assertEquals(countBefore,countAfter);
        for (int i = 0; i < toBeSaved.size(); i++) {
            assertEquals(toBeSaved.get(i),milestones.findById(toBeSaved.get(i).getId()).orElse(null));
        }
    }

    @Test
    void findById() {
        assertEquals(0, milestones.getById(0).getId());
        assertEquals(testData.get(0), milestones.findById(0).orElse(null));
    }

    @Test
    void existsById() {
        assertTrue(milestones.existsById(1));
    }

    @Test
    void getById() {
        assertEquals(0, milestones.getById(0).getId());
        assertEquals(testData.get(0), milestones.getById(0));
    }

    @Test
    void flush() {
       milestones.flush();
       assertEquals(0,milestones.count());
    }
}
