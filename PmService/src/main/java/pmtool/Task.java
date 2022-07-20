package pmtool;

import java.time.Duration;
import java.util.List;

public class Task {
    private int id;
    private String name;
    private Duration requiredTime;
    private List<Task> subtasks;
    private List<User> assignedUsers;

    public Task(int id, String name, Duration requiredTime) {
        this.id = id;
        this.name=name;
        this.requiredTime = requiredTime;
    }

    public Task(String name, Duration requiredTime) {
        this.id = id;
        this.name=name;
        this.requiredTime = requiredTime;
    }
    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public void addSubtask(Task subtask){
        subtasks.add(subtask);
    }

    public void removeSubtask(Task subtask){
        subtasks.remove(subtask);
    }

    public void addAssignedUser(User assignedUser){
        assignedUsers.add(assignedUser);
    }

    public void removeAssignedUser(User assignedUser){
        assignedUsers.remove(assignedUser);
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

    public Duration getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Duration requiredTime) {
        this.requiredTime = requiredTime;
    }
}
