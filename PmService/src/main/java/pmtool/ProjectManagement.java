package pmtool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectManagement {
    void addProject(Project project);

    void addMilestone(Project project, Milestone milestone);

    void addDelivery(Milestone milestone, Delivery delivery);

    void addTask(Milestone milestone, Task task);

    void addTask(Task task, Task SubTask);

    void removeProject(Project project);

    void removeMilestone(Milestone milestone);

    void removeDelivery(Delivery delivery);

    void removeTask(Task task);

    List<Project> getAllProjects();

    List<Milestone> getAllMilestones();

    List<Delivery> getAllDeliveries();

    List<Task> getAllTasks();

    List<Milestone> getAllMilestonesByProject(Project project);

    List<Delivery> getAllDeliveriesByMilestone(Milestone milestone);

    List<Task> getAllTasksByMilestone(Milestone milestone);

    List<Task> getAllSubTasksByTask(Task task);

    List<Task> getAllTasksByProject(Project project);

    void editProjectName(Project project, String name);

    void editProjectStatus(Project project, ProjectStatus status);

    void editProjectStartTime(Project project, LocalDateTime startTime);

    void editProjectClientName(Project project, String clientName);

    void editProjectBackOfficeName(Project project, String backOfficeName);

    void editMilestoneName(Milestone milestone, String name);

    //    void editMilestoneStatus(Milestone milestone, MilestoneStatus status);
    void editMilestoneDueDate(Milestone milestone, LocalDateTime dueDate);

    void editMilestoneRequiredTime(Milestone milestone, Duration requiredTime);

    void editDeliveryName(Delivery delivery, String name);
//    void editDeliveryStatus(Delivery delivery, DeliveryStatus status);


    void editTaskName(Task task, String name);

    //    void editTaskStatus(Task task, TaskStatus status);
    void editTaskRequiredTime(Task task, Duration requiredTime);
}
