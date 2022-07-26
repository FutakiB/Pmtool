package pmtool;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Delivery delivery = new Delivery(4, 4, "Delivery 4", DeliveryType.ARTIFACT);
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
        Project project = new Project();

        pmService.removeProject(project);

        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    void removeMilestone() {
        Milestone milestone = new Milestone();

        pmService.removeMilestone(milestone);

        verify(milestoneRepository, times(1)).delete(milestone);
    }

    @Test
    void removeDelivery() {
        Delivery delivery = new Delivery();

        pmService.removeDelivery(delivery);

        verify(deliveryRepository, times(1)).delete(delivery);
    }

    @Test
    void removeTask() {
        Task task = new Task();

        pmService.removeTask(task);

        verify(taskRepository, times(1)).delete(task);
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
    void editProjectNameSuccessful() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        pmService.editProjectName(project, "Project 1 edited");
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void editProjectNameProjectIsNotExists() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectName(project, "Project 1 edited");
        });
        assertEquals("Project does not exist", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectNameNameIsEmpty() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectName(project, "");
        });
        assertEquals("Project name cannot be empty", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectNameNameTooLong() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectName(project, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        });
        assertEquals("Project name cannot be longer than 50 characters", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectStatusSuccess() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        pmService.editProjectStatus(project, ProjectStatus.COMPLETED);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void editProjectStatusProjectNotExists() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectStatus(project, ProjectStatus.COMPLETED);
        });
        assertEquals("Project does not exist", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectStartTimeSuccessful() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        pmService.editProjectStartTime(project, LocalDateTime.now());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void editProjectStartTimeProjectNotExists() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectStartTime(project, LocalDateTime.now());
        });
        assertEquals("Project does not exist", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectClientNameSuccessful() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        pmService.editProjectClientName(project, "Client 1 edited");
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void editProjectClientProjectNotExists() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectClientName(project, "Client 1 edited");
        });
        assertEquals("Project does not exist", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectClientNameIsEmpty() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectClientName(project, "");
        });
        assertEquals("Client name cannot be empty", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectClientNameTooLong() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectClientName(project, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        });
        assertEquals("Client name cannot be longer than 50 characters", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectBackOfficeNameSuccessful() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        pmService.editProjectBackOfficeName(project, "BackOffice 1 edited");
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void editProjectBackOfficeProjectNotExists() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectBackOfficeName(project, "BackOffice 1 edited");
        });
        assertEquals("Project does not exist", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectBackOfficeNameIsEmpty() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectBackOfficeName(project, "");
        });
        assertEquals("Back office name cannot be empty", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editProjectBackOfficeNameTooLong() {
        Project project = new Project(1, "Project 1", ProjectStatus.IN_PROGRESS, LocalDateTime.now(), "Client 1", "BackOffice 1");
        when(projectRepository.existsById(project.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editProjectBackOfficeName(project, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        });
        assertEquals("Back office name cannot be longer than 50 characters", exception.getMessage());
        verify(projectRepository, times(0)).save(project);
    }

    @Test
    void editMilestoneNameSuccessful() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(true);
        pmService.editMilestoneName(milestone, "Milestone 1 edited");
        verify(milestoneRepository, times(1)).save(milestone);
    }

    @Test
    void editMilestoneNameNotExist() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editMilestoneName(milestone, "Milestone 1 edited");
        });
        assertEquals("Milestone does not exist", exception.getMessage());
        verify(milestoneRepository, times(0)).save(milestone);
    }

    @Test
    void editMilestoneNameIsEmpty() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editMilestoneName(milestone, "");
        });
        assertEquals("Milestone name cannot be empty", exception.getMessage());
        verify(milestoneRepository, times(0)).save(milestone);
    }

    @Test
    void editMilestoneNameTooLong() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editMilestoneName(milestone, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        });
        assertEquals("Milestone name cannot be longer than 50 characters", exception.getMessage());
        verify(milestoneRepository, times(0)).save(milestone);
    }

    @Test
    void editMilestoneDueDateSuccessful() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(true);
        pmService.editMilestoneDueDate(milestone, LocalDateTime.now().plusDays(1));
        verify(milestoneRepository, times(1)).save(milestone);
    }

    @Test
    void editMilestoneDueDateNotExists() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editMilestoneDueDate(milestone, LocalDateTime.now().plusDays(1));
        });
        assertEquals("Milestone does not exist", exception.getMessage());
        verify(milestoneRepository, times(0)).save(milestone);
    }

    @Test
    void editMilestoneRequiredTimeSuccessful() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(true);
        pmService.editMilestoneRequiredTime(milestone, Duration.ofHours(1));
        verify(milestoneRepository, times(1)).save(milestone);
    }

    @Test
    void editMilestoneRequiredTimeNotExists() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editMilestoneRequiredTime(milestone, Duration.ofHours(1));
        });
        assertEquals("Milestone does not exist", exception.getMessage());
        verify(milestoneRepository, times(0)).save(milestone);
    }

    @Test
    void editMilestoneRequiredTimeIsNegative() {
        Milestone milestone = new Milestone(1, 1, "Milestone 1", LocalDateTime.now(), Duration.ZERO);
        when(milestoneRepository.existsById(milestone.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editMilestoneRequiredTime(milestone, Duration.ofHours(-1));
        });
        assertEquals("Required time cannot be negative", exception.getMessage());
        verify(milestoneRepository, times(0)).save(milestone);
    }

    @Test
    void editDeliveryNameSuccessful() {
        Delivery delivery = new Delivery(1, 1, "Delivery 1", DeliveryType.ARTIFACT);
        when(deliveryRepository.existsById(delivery.getId())).thenReturn(true);
        pmService.editDeliveryName(delivery, "Delivery 1 edited");
        verify(deliveryRepository, times(1)).save(delivery);
    }

    @Test
    void editDeliveryNameNotExists() {
        Delivery delivery = new Delivery(1, 1, "Delivery 1", DeliveryType.ARTIFACT);
        when(deliveryRepository.existsById(delivery.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editDeliveryName(delivery, "Delivery 1 edited");
        });
        assertEquals("Delivery does not exist", exception.getMessage());
        verify(deliveryRepository, times(0)).save(delivery);
    }

    @Test
    void editDeliveryNameIsEmpty() {
        Delivery delivery = new Delivery(1, 1, "Delivery 1", DeliveryType.ARTIFACT);
        when(deliveryRepository.existsById(delivery.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editDeliveryName(delivery, "");
        });
        assertEquals("Delivery name cannot be empty", exception.getMessage());
        verify(deliveryRepository, times(0)).save(delivery);
    }

    @Test
    void editDeliveryNameTooLong() {
        Delivery delivery = new Delivery(1, 1, "Delivery 1", DeliveryType.ARTIFACT);
        when(deliveryRepository.existsById(delivery.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editDeliveryName(delivery, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        });
        assertEquals("Delivery name cannot be longer than 50 characters", exception.getMessage());
        verify(deliveryRepository, times(0)).save(delivery);
    }

    @Test
    void editTaskNameSuccessful() {
        Task task = new Task(1, 1, 1, null, "Task 1", Duration.ZERO);
        when(taskRepository.existsById(task.getId())).thenReturn(true);
        pmService.editTaskName(task, "Task 1 edited");
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void editTaskNameNotExists() {
        Task task = new Task(1, 1, 1, null, "Task 1", Duration.ZERO);
        when(taskRepository.existsById(task.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editTaskName(task, "Task 1 edited");
        });
        assertEquals("Task does not exist", exception.getMessage());
        verify(taskRepository, times(0)).save(task);
    }

    @Test
    void editTaskNameIsEmpty() {
        Task task = new Task(1, 1, 1, null, "Task 1", Duration.ZERO);
        when(taskRepository.existsById(task.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editTaskName(task, "");
        });
        assertEquals("Task name cannot be empty", exception.getMessage());
        verify(taskRepository, times(0)).save(task);
    }

    @Test
    void editTaskNameTooLong() {
        Task task = new Task(1, 1, 1, null, "Task 1", Duration.ZERO);
        when(taskRepository.existsById(task.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editTaskName(task, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        });
        assertEquals("Task name cannot be longer than 50 characters", exception.getMessage());
        verify(taskRepository, times(0)).save(task);
    }

    @Test
    void editTaskRequiredTimeSuccessful() {
        Task task = new Task(1, 1, 1, null, "Task 1", Duration.ZERO);
        when(taskRepository.existsById(task.getId())).thenReturn(true);
        pmService.editTaskRequiredTime(task, Duration.ofHours(1));
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void editTaskRequiredTimeNotExists() {
        Task task = new Task(1, 1, 1, null, "Task 1", Duration.ZERO);
        when(taskRepository.existsById(task.getId())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editTaskRequiredTime(task, Duration.ofHours(1));
        });
        assertEquals("Task does not exist", exception.getMessage());
        verify(taskRepository, times(0)).save(task);
    }

    @Test
    void editTaskRequiredTimeIsNegative() {
        Task task = new Task(1, 1, 1, null, "Task 1", Duration.ZERO);
        when(taskRepository.existsById(task.getId())).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pmService.editTaskRequiredTime(task, Duration.ofHours(-1));
        });
        assertEquals("Required time cannot be negative", exception.getMessage());
        verify(taskRepository, times(0)).save(task);
    }
}