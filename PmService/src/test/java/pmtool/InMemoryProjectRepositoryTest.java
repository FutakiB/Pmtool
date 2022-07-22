package pmtool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryProjectRepositoryTest {
    static InMemoryProjectRepository projects = new InMemoryProjectRepository();


    @BeforeEach
    void setUpEach() {
        projects.getDb().clear();
        projects.getDb().add(new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1"));
        projects.getDb().add(new Project(2, "Project 2", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 2", "BackOffice 2"));
        projects.getDb().add(new Project(3, "Project 3", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 3", "BackOffice 3"));
    }

    @Test
    void findAll() {
        assertEquals(3, projects.findAll().size());
    }

    @Test
    void findAllById() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        assertEquals(3, projects.findAllById(ids).size());
    }

    @Test
    void count() {
        assertEquals(3, projects.count());
    }

    @Test
    void deleteById() {
        projects.deleteById(2);
        assertEquals(1, projects.getDb().get(0).getId());
        assertEquals(3, projects.getDb().get(1).getId());
        assertEquals(2, projects.getDb().size());
    }

    @Test
    void delete() {
        projects.delete(projects.getDb().get(0));

        assertEquals(2, projects.getDb().size());
        assertEquals(2, projects.getDb().get(0).getId());
        assertEquals(3, projects.getDb().get(1).getId());
    }

    @Test
    void deleteAllById() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        projects.deleteAllById(ids);
        assertEquals(0, projects.getDb().size());
    }

    @Test
    void testDeleteAll() {
        projects.getDb().clear();
        Project p = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        Project p2 = new Project(2, "Project 2", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 2", "BackOffice 2");
        ArrayList<Project> ps = new ArrayList<>();
        ps.add(p);
        ps.add(p2);
        projects.getDb().add(p);
        projects.getDb().add(p2);
        projects.deleteAll(ps);
        assertEquals(0, projects.getDb().size());
    }

    @Test
    void deleteAll() {
        projects.deleteAll();
        assertEquals(0, projects.getDb().size());
    }

    @Test
    void save() {
        projects.getDb().clear();
        Project project = new Project(0, "Project 4", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 4", "BackOffice 4");
        projects.save(project);
        project = new Project(0, "Project 5", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 5", "BackOffice 5");
        projects.save(project);
        assertEquals(1, projects.getDb().get(0).getId());
        assertEquals(2, projects.getDb().get(1).getId());
    }

    @Test
    void saveAll() {
        projects.getDb().clear();
        Project project = new Project(0, "Project 4", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 4", "BackOffice 4");
        Project project2 = new Project(0, "Project 5", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 5", "BackOffice 5");
        ArrayList<Project> ps = new ArrayList<>();
        ps.add(project);
        ps.add(project2);
        projects.saveAll(ps);
        assertEquals(2, projects.getDb().size());
    }

    @Test
    void findById() {
        projects.findById(1).ifPresent(p -> assertEquals(1, p.getId()));
    }

    @Test
    void existsById() {
        assertTrue(projects.existsById(1));
    }

    @Test
    void getById() {
        assertEquals(1, projects.getById(1).getId());
    }
}