package pmtool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PmServiceTest {

    InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();
    InMemoryMilestoneRepository milestoneRepository = new InMemoryMilestoneRepository();
    InMemoryDeliveryRepository deliveryRepository = new InMemoryDeliveryRepository();
    InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();

    PmService pmService = new PmService(milestoneRepository, deliveryRepository, taskRepository, projectRepository);

    @BeforeEach
    void setUp() {
        projectRepository.deleteAll();
        milestoneRepository.deleteAll();
        deliveryRepository.deleteAll();
        taskRepository.deleteAll();

        projectRepository.save(new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1"));
        milestoneRepository.save(new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO));
        deliveryRepository.save(new Delivery(1, "Delivery 1", DeliveryType.ARTIFACT, 1));
        taskRepository.save(new Task(1, 1, 1, null, "Task 1", Duration.ZERO));
        taskRepository.save(new Task(2, 1, 1, 1, "SubTask 1", Duration.ZERO));

        projectRepository.save(new Project(2, "Project 2", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 2", "BackOffice 2"));
        milestoneRepository.save(new Milestone(2, 2, "Milestone 2", LocalDateTime.now(), Duration.ZERO));
        deliveryRepository.save(new Delivery(2, "Delivery 2", DeliveryType.ARTIFACT, 2));
        taskRepository.save(new Task(3, 2, 2, null, "Task 2", Duration.ZERO));
        taskRepository.save(new Task(4, 2, 2, 3, "SubTask 2", Duration.ZERO));

        projectRepository.save(new Project(3, "Project 3", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 3", "BackOffice 3"));
        milestoneRepository.save(new Milestone(3, 3, "Milestone 3", LocalDateTime.now(), Duration.ZERO));
        deliveryRepository.save(new Delivery(3, "Delivery 3", DeliveryType.ARTIFACT, 3));
        taskRepository.save(new Task(5, 3, 3, null, "Task 3", Duration.ZERO));
        taskRepository.save(new Task(6, 3, 3, 5, "SubTask 3", Duration.ZERO));
    }

    @Test
    void addProject() {
        Project project = new Project(4, "Project 4", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 4", "BackOffice 4");
        pmService.addProject(project);
        assertEquals(4, projectRepository.findAll().size());
    }

    @Test
    void addMilestone() {
//        Milestone milestone = new Milestone(4, 4, "Milestone 4", LocalDateTime.now(), Duration.ZERO);
//        pmService.addMilestone(projectRepository.findById(1), milestone);
//        assertEquals(4, milestoneRepository.findAll().size());
    }

    @Test
    void addDelivery() {
        Delivery delivery = new Delivery(4, "Delivery 4", DeliveryType.ARTIFACT, 4);
        pmService.addDelivery(milestoneRepository.getOne(1), delivery);
        assertEquals(4, deliveryRepository.findAll().size());
    }

    @Test
    void addTask() {
//        Task task = new Task(4, 4, 4, null, "Task 4", Duration.ZERO);
//        pmService.addTask(milestoneRepository.getOne(1), task);
//        assertEquals(4, taskRepository.findAll().size());
    }

    @Test
    void testAddTask() {
//        Task task = new Task(4, 4, 4, null, "Task 4", Duration.ZERO);
//        pmService.addTask(taskRepository.getOne(1), task);
//        assertEquals(4, taskRepository.findAll().size());
    }

    @Test
    void removeProject() {
//        pmService.removeProject(projectRepository.getOne(1));
//        assertEquals(2, projectRepository.findAll().size());
    }

    @Test
    void removeMilestone() {
        pmService.removeMilestone(milestoneRepository.getOne(1));
        assertEquals(2, milestoneRepository.findAll().size());
    }

    @Test
    void removeDelivery() {
//        pmService.removeDelivery(deliveryRepository.getOne(1));
//        assertEquals(2, deliveryRepository.findAll().size());
    }

    @Test
    void removeTask() {
        pmService.removeTask(taskRepository.getOne(1));
        assertEquals(5, taskRepository.findAll().size());
    }

    @Test
    void getAllProjects() {
        assertEquals(3, pmService.getAllProjects().size());
    }

    @Test
    void getAllMilestones() {
        assertEquals(3, pmService.getAllMilestones().size());
    }

    @Test
    void getAllDeliveries() {
        assertEquals(3, pmService.getAllDeliveries().size());
    }

    @Test
    void getAllTasks() {
        assertEquals(6, pmService.getAllTasks().size());
    }

    @Test
    void getAllSubTasks() {
    }

    @Test
    void getAllMilestonesByProject() {
//        assertEquals(3, pmService.getAllMilestonesByProject(projectRepository.getOne(1)).size());
    }

    @Test
    void getAllDeliveriesByMilestone() {
//        assertEquals(3, pmService.getAllDeliveriesByMilestone(milestoneRepository.getOne(1)).size());
    }

    @Test
    void getAllTasksByMilestone() {
//        assertEquals(6, pmService.getAllTasksByMilestone(milestoneRepository.getOne(1)).size());
    }

    @Test
    void getAllSubTasksByTask() {
        //assertEquals(2, pmService.getAllSubTasksByTask(taskRepository.getOne(1)).size());
    }

    @Test
    void getAllTasksByProject() {
//        assertEquals(6, pmService.getAllTasksByProject(projectRepository.getOne(1)).size());
    }
}