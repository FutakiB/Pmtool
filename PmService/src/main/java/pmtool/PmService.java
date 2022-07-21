package pmtool;

import java.util.ArrayList;
import java.util.List;

public class PmService implements ProjectManagement {
    private final InMemoryProjectRepository projectRepository;
    private final InMemoryMilestoneRepository milestoneRepository;
    private final InMemoryDeliveryRepository deliveryRepository;
    private final InMemoryTaskRepository taskRepository;


    public PmService(InMemoryMilestoneRepository milestoneRepository,
                     InMemoryDeliveryRepository deliveryRepository,
                     InMemoryTaskRepository taskRepository,
                     InMemoryProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.deliveryRepository = deliveryRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void addMilestone(Project project, Milestone milestone) {
        milestoneRepository.save(milestone);
        project.addMilestone(milestone);
    }

    @Override
    public void addDelivery(Milestone milestone, Delivery delivery) {
        deliveryRepository.save(delivery);
        milestone.addDelivery(delivery);
    }

    @Override
    public void addTask(Milestone milestone, Task task) {
        taskRepository.save(task);
        milestone.addTask(task);
    }

    @Override
    public void addTask(Task task, Task SubTask) {
        taskRepository.save(SubTask);
        task.addSubtask(SubTask);
    }

    @Override
    public void removeProject(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public void removeMilestone(Milestone milestone) {
        milestoneRepository.delete(milestone);
    }

    @Override
    public void removeDelivery(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    @Override
    public void removeTask(Task task) {
        taskRepository.delete(task);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllSubTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Milestone> getAllMilestonesByProject(Project project) {
        return project.getMilestones();
    }

    @Override
    public List<Delivery> getAllDeliveriesByMilestone(Milestone milestone) {
        return milestone.getDeliveries();
    }

    @Override
    public List<Task> getAllTasksByMilestone(Milestone milestone) {
        return milestone.getTasks();
    }

    @Override
    public List<Task> getAllSubTasksByTask(Task task) {
        return task.getSubtasks();
    }

    @Override
    public List<Task> getAllTasksByProject(Project project) {
        List<Task> result = new ArrayList<>();
        for (Milestone milestone : project.getMilestones()) {
            result.addAll(milestone.getTasks());
        }
        return result;
    }
}
