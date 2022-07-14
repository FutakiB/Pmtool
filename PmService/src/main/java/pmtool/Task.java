package pmtool;

import java.util.List;

public class Task {
    private int id;
    private String name;
    private int requiredDays;
    private List<Task> subtasks;

    public Task(int id, int requiredDays) {
        this.id = id;
        this.requiredDays = requiredDays;
    }

    public void addSubtask(Task subtask){
        subtasks.add(subtask);
    }

    public void removeSubtask(Task subtask){
        subtasks.remove(subtask);
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
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

    public int getRequiredDays() {
        return requiredDays;
    }

    public void setRequiredDays(int requiredDays) {
        this.requiredDays = requiredDays;
    }
}
