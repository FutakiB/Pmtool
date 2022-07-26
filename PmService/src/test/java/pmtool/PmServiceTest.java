package pmtool;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class PmServiceTest {

    private InMemoryProjectRepository projectRepository;
    private InMemoryMilestoneRepository milestoneRepository;
    private InMemoryDeliveryRepository deliveryRepository;
    private InMemoryTaskRepository taskRepository;

    private PmService pmService;

    @BeforeEach
    public void setup() {
        projectRepository = mock(InMemoryProjectRepository.class);
        milestoneRepository = mock(InMemoryMilestoneRepository.class);
        deliveryRepository = mock(InMemoryDeliveryRepository.class);
        taskRepository = mock(InMemoryTaskRepository.class);
        pmService = new PmService(milestoneRepository, deliveryRepository, taskRepository, projectRepository);
    }


//    @BeforeEach
//    void setUp() {
//        projectRepository.deleteAll();
//        milestoneRepository.deleteAll();
//        deliveryRepository.deleteAll();
//        taskRepository.deleteAll();
//
//        projectRepository.save(new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1"));
//        milestoneRepository.save(new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO));
//        deliveryRepository.save(new Delivery(1, "Delivery 1", DeliveryType.ARTIFACT, 1));
//        taskRepository.save(new Task(1, 1, 1, null, "Task 1", Duration.ZERO));
//        taskRepository.save(new Task(2, 1, 1, 1, "SubTask 1", Duration.ZERO));
//
//        projectRepository.save(new Project(2, "Project 2", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 2", "BackOffice 2"));
//        milestoneRepository.save(new Milestone(2, 2, "Milestone 2", LocalDateTime.now(), Duration.ZERO));
//        deliveryRepository.save(new Delivery(2, "Delivery 2", DeliveryType.ARTIFACT, 2));
//        taskRepository.save(new Task(3, 2, 2, null, "Task 2", Duration.ZERO));
//        taskRepository.save(new Task(4, 2, 2, 3, "SubTask 2", Duration.ZERO));
//
//        projectRepository.save(new Project(3, "Project 3", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 3", "BackOffice 3"));
//        milestoneRepository.save(new Milestone(3, 3, "Milestone 3", LocalDateTime.now(), Duration.ZERO));
//        deliveryRepository.save(new Delivery(3, "Delivery 3", DeliveryType.ARTIFACT, 3));
//        taskRepository.save(new Task(5, 3, 3, null, "Task 3", Duration.ZERO));
//        taskRepository.save(new Task(6, 3, 3, 5, "SubTask 3", Duration.ZERO));
//    }

    @Test
    void addProject() {
        Project project = new Project(4, "Project 4", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 4", "BackOffice 4");
        pmService.addProject(project);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void addMilestone() {
        Milestone milestone = new Milestone(4, 4, "Milestone 4", LocalDateTime.now(), Duration.ZERO);
        Project p = new Project(1, "Project 4", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 4", "BackOffice 4");
        pmService.addMilestone(p, milestone);
        verify(milestoneRepository, times(1)).save(milestone);
    }

    @Test
    void addDelivery() {
        Milestone milestone = new Milestone(4, 4, "Milestone 4", LocalDateTime.now(), Duration.ZERO);
        Delivery delivery = new Delivery(4, "Delivery 4", DeliveryType.ARTIFACT, 4);
        pmService.addDelivery(milestone, delivery);
        verify(deliveryRepository, times(1)).save(delivery);
    }

    @Test
    void addTask() {
        Task task = new Task(10, 1, 1, null, "Task 4", Duration.ZERO);
        Milestone milestone = new Milestone(4, 4, "Milestone 4", LocalDateTime.now(), Duration.ZERO);
        pmService.addTask(milestone, task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testAddSubTask() {
        Task task = new Task(11, 1, 1, null, "Task 4", Duration.ZERO);
        Task subTask = new Task(12, 1, 1, task.getId(), "SubTask 4", Duration.ZERO);
        pmService.addTask(task, subTask);
        verify(taskRepository, times(1)).save(subTask);
    }

    @Test
    void removeProject() {
        pmService.removeProject(projectRepository.getOne(1));
        verify(projectRepository, times(1)).delete(projectRepository.getOne(1));
    }

    @Test
    void removeMilestone() {
//        pmService.removeMilestone(milestoneRepository.getOne(1));
//        assertEquals(2, milestoneRepository.findAll().size());
    }

    @Test
    void removeDelivery() {
//        pmService.removeDelivery(deliveryRepository.getOne(1));
//        assertEquals(2, deliveryRepository.findAll().size());
    }

    @Test
    void removeTask() {
//        pmService.removeTask(taskRepository.getOne(1));
//        assertEquals(5, taskRepository.findAll().size());
    }

    @Test
    void getAllProjects() {
//        assertEquals(3, pmService.getAllProjects().size());
    }

    @Test
    void getAllMilestones() {
//        assertEquals(3, pmService.getAllMilestones().size());
    }

    @Test
    void getAllDeliveries() {
//        assertEquals(3, pmService.getAllDeliveries().size());
    }

    @Test
    void getAllTasks() {
//        assertEquals(6, pmService.getAllTasks().size());
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

    @Test
    void editProjectName() {
    }

    @Test
    void editProjectStatus() {
    }

    @Test
    void editProjectStartTime() {
    }

    @Test
    void editProjectClientName() {
    }

    @Test
    void editProjectBackOfficeName() {
    }

    @Test
    void editMilestoneName() {
    }

    @Test
    void editMilestoneDueDate() {
    }

    @Test
    void editMilestoneRequiredTime() {
    }

    @Test
    void editDeliveryName() {
    }

    @Test
    void editTaskName() {
    }

    @Test
    void editTaskRequiredTime() {
    }
}