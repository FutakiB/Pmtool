package pmtool;

import java.time.LocalDateTime;
import java.util.List;

public class Milestone {
    private int id;
    private String name;
    private LocalDateTime dueDate;
    private int requiredDays;
    private List<Task> tasks;
    private List<Task> deliverys;

    public Milestone(int id, String name, LocalDateTime dueDate, int requiredDays) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.requiredDays = requiredDays;
    }

    public void addSubtask(Task task){
        tasks.add(task);
    }

    public void removeSubtask(Task task){
        tasks.remove(task);
    }

    public void addDelivery(Task delivery){
        deliverys.add(delivery);
    }

    public void removeDelivery(Task delivery){
        deliverys.remove(delivery);
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

    public int getRequiredDays() {
        return requiredDays;
    }

    public void setRequiredDays(int requiredDays) {
        this.requiredDays = requiredDays;
    }
}
