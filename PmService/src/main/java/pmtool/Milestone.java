package pmtool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class Milestone {
    private int id;
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

    public void addSubtask(Task task){
        tasks.add(task);
    }

    public void removeSubtask(Task task){
        tasks.remove(task);
    }

    public void addDelivery(Delivery delivery){
        deliveries.add(delivery);
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
}
