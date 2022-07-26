package pmtool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        milestoneRepository.findAll().stream()
                .filter(milestone -> milestone.getProjectId() == project.getId())
                .forEach(this::removeMilestone);
        projectRepository.delete(project);
    }

    @Override
    public void removeMilestone(Milestone milestone) {
        taskRepository.findAll().stream()
                .filter(task -> task.getMilestoneId() == milestone.getId())
                .forEach(this::removeTask);
        deliveryRepository.findAll().stream()
                .filter(delivery -> delivery.getMilestoneId() == milestone.getId())
                .forEach(this::removeDelivery);
        milestoneRepository.delete(milestone);
    }

    @Override
    public void removeDelivery(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    @Override
    public void removeTask(Task task) {
        taskRepository.findAll().stream().filter(t -> t.getParentTaskId() == task.getId()).forEach(this::removeTask);
        taskRepository.delete(task);
    }

    @Override
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
    public List<Milestone> getAllMilestonesByProject(Project project) {
        return milestoneRepository.findAll().stream()
                .filter(t -> t.getProjectId() == project.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Delivery> getAllDeliveriesByMilestone(Milestone milestone) {
        return deliveryRepository.findAll().stream()
                .filter(t -> t.getMilestoneId() == milestone.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksByMilestone(Milestone milestone) {
        return taskRepository.findAll().stream()
                .filter(t -> t.getMilestoneId() == milestone.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllSubTasksByTask(Task task) {
        return taskRepository.findAll().stream()
                .filter(t -> t.getParentTaskId() == task.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksByProject(Project project) {
        return taskRepository.findAll().stream()
                .filter(t -> t.getProjectId() == project.getId())
                .collect(Collectors.toList());
    }

    @Override
    public void editProjectName(Project project, String name) throws IllegalArgumentException {
        if (projectRepository.existsById(project.getId())) {
            if (project.getName().equals(name)) {
                return;
            }
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Project name cannot be empty");
            }
            if (name.length() > 50) {
                throw new IllegalArgumentException("Project name cannot be longer than 50 characters");
            }

            project.setName(name);
            projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("Project does not exist");
        }
    }

    @Override
    public void editProjectStatus(Project project, ProjectStatus status) throws IllegalArgumentException {
        project.setStatus(status);
        if (projectRepository.existsById(project.getId())) {
            projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("Project does not exist");
        }
    }

    @Override
    public void editProjectStartTime(Project project, LocalDateTime startTime) throws IllegalArgumentException {

        if (projectRepository.existsById(project.getId())) {
            if (startTime.isBefore(LocalDateTime.MIN)) {
                throw new IllegalArgumentException("Start time cannot be before 1970-01-01T00:00:00");
            }
            if (startTime.isAfter(LocalDateTime.MAX)) {
                throw new IllegalArgumentException("Start time cannot be after 9999-12-31T23:59:59");
            }
            project.setStartTime(startTime);
            projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("Project does not exist");
        }
    }

    @Override
    public void editProjectClientName(Project project, String clientName) throws IllegalArgumentException {
        if (projectRepository.existsById(project.getId())) {
            if (clientName.isEmpty()) {
                throw new IllegalArgumentException("Client name cannot be empty");
            }
            if (clientName.length() > 50) {
                throw new IllegalArgumentException("Client name cannot be longer than 50 characters");
            }
            project.setClientName(clientName);
            projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("Project does not exist");
        }

    }

    @Override
    public void editProjectBackOfficeName(Project project, String backOfficeName) throws IllegalArgumentException {
        if (projectRepository.existsById(project.getId())) {
            if (backOfficeName.isEmpty()) {
                throw new IllegalArgumentException("Back office name cannot be empty");
            }
            if (backOfficeName.length() > 50) {
                throw new IllegalArgumentException("Back office name cannot be longer than 50 characters");
            }
            project.setBackOfficeName(backOfficeName);
            projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("Project does not exist");
        }
    }

    @Override
    public void editMilestoneName(Milestone milestone, String name) throws IllegalArgumentException {
        if (milestoneRepository.existsById(milestone.getId())) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Milestone name cannot be empty");
            }
            if (name.length() > 50) {
                throw new IllegalArgumentException("Milestone name cannot be longer than 50 characters");
            }
            milestone.setName(name);
            milestoneRepository.save(milestone);
        } else {
            throw new IllegalArgumentException("Milestone does not exist");
        }
    }

    @Override
    public void editMilestoneDueDate(Milestone milestone, LocalDateTime dueDate) throws IllegalArgumentException {
        if (milestoneRepository.existsById(milestone.getId())) {
            if (dueDate.isBefore(LocalDateTime.MIN)) {
                throw new IllegalArgumentException("Due date cannot be before 1970-01-01T00:00:00");
            }
            if (dueDate.isAfter(LocalDateTime.MAX)) {
                throw new IllegalArgumentException("Due date cannot be after 9999-12-31T23:59:59");
            }
            milestone.setDueDate(dueDate);
            milestoneRepository.save(milestone);
        } else {
            throw new IllegalArgumentException("Milestone does not exist");
        }
    }

    @Override
    public void editMilestoneRequiredTime(Milestone milestone, Duration requiredTime) {
        if (milestoneRepository.existsById(milestone.getId())) {
            if (requiredTime.isNegative()) {
                throw new IllegalArgumentException("Required time cannot be negative");
            }
            milestone.setRequiredTime(requiredTime);
            milestoneRepository.save(milestone);
        } else {
            throw new IllegalArgumentException("Milestone does not exist");
        }
    }

    @Override
    public void editMilestoneStatus(Milestone milestone, MilestoneStatus status) {
        milestone.setStatus(status);
        if (milestoneRepository.existsById(milestone.getId())) {
            milestoneRepository.save(milestone);
        } else {
            throw new IllegalArgumentException("Milestone does not exist");
        }
    }


    @Override
    public void editDeliveryName(Delivery delivery, String name) throws IllegalArgumentException {
        if (deliveryRepository.existsById(delivery.getId())) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Delivery name cannot be empty");
            }
            if (name.length() > 50) {
                throw new IllegalArgumentException("Delivery name cannot be longer than 50 characters");
            }
            delivery.setName(name);
            deliveryRepository.save(delivery);
        } else {
            throw new IllegalArgumentException("Delivery does not exist");
        }
    }

    @Override
    public void editDeliveryStatus(Delivery delivery, DeliveryStatus status) {
        delivery.setStatus(status);
        if (deliveryRepository.existsById(delivery.getId())) {
            deliveryRepository.save(delivery);
        } else {
            throw new IllegalArgumentException("Delivery does not exist");
        }
    }

    @Override
    public void editTaskName(Task task, String name) throws IllegalArgumentException {
        if (taskRepository.existsById(task.getId())) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Task name cannot be empty");
            }
            if (name.length() > 50) {
                throw new IllegalArgumentException("Task name cannot be longer than 50 characters");
            }
            task.setName(name);
            taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task does not exist");
        }
    }

    @Override
    public void editTaskRequiredTime(Task task, Duration requiredTime) throws IllegalArgumentException {
        if (taskRepository.existsById(task.getId())) {
            if (requiredTime.isNegative()) {
                throw new IllegalArgumentException("Required time cannot be negative");
            }
            task.setRequiredTime(requiredTime);
            taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task does not exist");
        }
    }

    @Override
    public void editTaskStatus(Task task, TaskStatus status) {
        task.setStatus(status);
        if (taskRepository.existsById(task.getId())) {
            taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task does not exist");
        }
    }
}
