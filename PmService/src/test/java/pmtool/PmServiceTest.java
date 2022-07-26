package pmtool;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        Project p1 = new Project(1, "Project", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client1", "Back office1");
        Milestone m1 = new Milestone(1, "Milestone1", LocalDateTime.now(), Duration.ofDays(30));
        m1.setProjectId(1);
        Milestone m2 = new Milestone(2, "Milestone2", LocalDateTime.now(), Duration.ofDays(30));
        m2.setProjectId(2);
        List<Milestone> milestones = new ArrayList<>();
        milestones.add(m1);
        milestones.add(m2);
        when(milestoneRepository.findAll()).thenReturn(milestones);

        pmService.removeProject(p1);

        //verify(pmService,times(1)).removeMilestone(any(Milestone.class));
        verify(milestoneRepository, times(1)).delete(any(Milestone.class));
        verify(projectRepository, times(1)).delete(p1);
    }

    @Test
    void removeMilestone() {
        Milestone m1 = new Milestone(1, "Milestone1", LocalDateTime.now(), Duration.ofDays(30));

        Task t1 = new Task(1, "Task1", Duration.ofMinutes(30));
        Task t2 = new Task(2, "Task2", Duration.ofMinutes(30));
        t2.setMilestoneId(2);
        List<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);
        when(taskRepository.findAll()).thenReturn(tasks);

        Delivery d1 = new Delivery(1, "Delivery1", DeliveryType.ARTIFACT, 2);
        t1.setMilestoneId(1);
        Delivery d2 = new Delivery(2, "Delivery2", DeliveryType.DOCUMENT, 1);
        t2.setMilestoneId(2);
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(d1);
        deliveries.add(d2);
        when(deliveryRepository.findAll()).thenReturn(deliveries);

        pmService.removeMilestone(m1);

        verify(taskRepository, times(1)).delete(any(Task.class));
        verify(deliveryRepository, times(1)).delete(any(Delivery.class));
        verify(milestoneRepository, times(1)).delete(m1);
    }

    @Test
    void removeDelivery() {
        Delivery delivery = new Delivery();

        pmService.removeDelivery(delivery);

        verify(deliveryRepository, times(1)).delete(delivery);
    }

    @Test
    void removeTask_calls_delete_on_all_subtasks() {
        Task t1 = new Task();
        t1.setId(1);
        Task t2 = new Task();
        t2.setId(2);
        Task t3 = new Task();
        t3.setId(3);
        t3.setParentTaskId(1);
        List<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        when(taskRepository.findAll()).thenReturn(tasks);

        pmService.removeTask(t1);

        verify(taskRepository, times(1)).delete(t1);
        verify(taskRepository, times(0)).delete(t2);
        verify(taskRepository, times(1)).delete(t3);
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