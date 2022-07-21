package pmtool;

import java.util.List;

public interface ProjectManagement {
    void addProject(Project project);

    void addMilestone(Project project, Milestone milestone);

    void addDelivery(Milestone milestone, Delivery delivery);

    void addTask(Milestone milestone, Task task);

    void removeProject(Project project);

    void removeMilestone(Milestone milestone);

    void removeDelivery(Delivery delivery);

    void removeTask(Task task);

    List<Project> getAllProjects();

    List<Milestone> getAllMilestones();

    List<Delivery> getAllDeliveries();

    List<Task> getAllTasks();
}
