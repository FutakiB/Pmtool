package pmtool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

class PmServiceTest {

    InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();
    InMemoryMilestoneRepository milestoneRepository = new InMemoryMilestoneRepository();
    InMemoryDeliveryRepository deliveryRepository = new InMemoryDeliveryRepository();
    InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();

    PmService pmService = new PmService(milestoneRepository, deliveryRepository, taskRepository, projectRepository);

    @BeforeEach
    void setUp() {
        projectRepository.getDb().clear();
        milestoneRepository.getDb().clear();
        deliveryRepository.getDb().clear();
        taskRepository.getDb().clear();

        projectRepository.getDb().add(new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1"));
        milestoneRepository.getDb().add(new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO));
        deliveryRepository.getDb().add(new Delivery(1, "Delivery 1", DeliveryType.ARTIFACT, 1));
        taskRepository.getDb().add(new Task(1, 1, 1, null, "Task 1", Duration.ZERO));
        taskRepository.getDb().add(new Task(2, 1, 1, 1, "SubTask 1", Duration.ZERO));

        projectRepository.getDb().add(new Project(2, "Project 2", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 2", "BackOffice 2"));
        milestoneRepository.getDb().add(new Milestone(2, 2, "Milestone 2", LocalDateTime.now(), Duration.ZERO));
        deliveryRepository.getDb().add(new Delivery(2, "Delivery 2", DeliveryType.ARTIFACT, 2));
        taskRepository.getDb().add(new Task(3, 2, 2, null, "Task 2", Duration.ZERO));
        taskRepository.getDb().add(new Task(4, 2, 2, 3, "SubTask 2", Duration.ZERO));

        projectRepository.getDb().add(new Project(3, "Project 3", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 3", "BackOffice 3"));
        milestoneRepository.getDb().add(new Milestone(3, 3, "Milestone 3", LocalDateTime.now(), Duration.ZERO));
        deliveryRepository.getDb().add(new Delivery(3, "Delivery 3", DeliveryType.ARTIFACT, 3));
        taskRepository.getDb().add(new Task(5, 3, 3, null, "Task 3", Duration.ZERO));
        taskRepository.getDb().add(new Task(6, 3, 3, 5, "SubTask 3", Duration.ZERO));
    }

    @Test
    void addProject() {
    }

    @Test
    void addMilestone() {
    }

    @Test
    void addDelivery() {
    }

    @Test
    void addTask() {
    }

    @Test
    void testAddTask() {
    }

    @Test
    void removeProject() {
    }

    @Test
    void removeMilestone() {
    }

    @Test
    void removeDelivery() {
    }

    @Test
    void removeTask() {
    }

    @Test
    void getAllProjects() {
    }

    @Test
    void getAllMilestones() {
    }

    @Test
    void getAllDeliveries() {
    }

    @Test
    void getAllTasks() {
    }

    @Test
    void getAllSubTasks() {
    }

    @Test
    void getAllMilestonesByProject() {
    }

    @Test
    void getAllDeliveriesByMilestone() {
    }

    @Test
    void getAllTasksByMilestone() {
    }

    @Test
    void getAllSubTasksByTask() {
    }

    @Test
    void getAllTasksByProject() {
    }
}