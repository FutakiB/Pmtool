package pmtool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Milestone {
    private Integer id;
    private Integer projectId;
    private String name;
    private LocalDateTime dueDate;
    private Duration requiredTime;
    private List<Task> tasks;
    private List<Delivery> deliveries;

    public Milestone(int id, String name, LocalDateTime dueDate, Duration requiredTime) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.requiredTime = requiredTime;
    }

    public Milestone() {
        this.id = null;
        this.projectId = null;
        this.name = "New milestone";
        this.dueDate = LocalDateTime.now();
        this.requiredTime = Duration.ZERO;
//        this.tasks = new ArrayList<>();
//        this.deliveries = new ArrayList<>();
    }

    public int getProjectId() {
        return projectId;
    }

    void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public Milestone(String name, LocalDateTime dueDate, Duration requiredTime) {
        this.name = name;
        this.dueDate = dueDate;
        this.requiredTime = requiredTime;
    }

    public void addTask(Task task) {
//        tasks.add(task);
        task.setMilestoneId(id);
    }

    public void removeSubtask(Task task){
        tasks.remove(task);
    }

    public void addDelivery(Delivery delivery) {
//        deliveries.add(delivery);
        delivery.setMilestoneId(id);
    }

    public void removeDelivery(Delivery delivery){
        deliveries.remove(delivery);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Duration getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Duration requiredTime) {
        this.requiredTime = requiredTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return id == milestone.id && Objects.equals(name, milestone.name) && Objects.equals(dueDate, milestone.dueDate) && Objects.equals(requiredTime, milestone.requiredTime) && Objects.equals(tasks, milestone.tasks) && Objects.equals(deliveries, milestone.deliveries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dueDate, requiredTime, tasks, deliveries);
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", requiredTime=" + requiredTime +
                ", tasks=" + tasks +
                ", deliveries=" + deliveries +
                '}';
    }
}
