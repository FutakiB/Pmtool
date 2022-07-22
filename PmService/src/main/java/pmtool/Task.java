package pmtool;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int id;
    private String name;
    private Duration requiredTime;
    private List<Task> subtasks;
    private List<User> assignedUsers;
    private int milestoneId;
    private int projectId;

    private int parentTaskId;

    public Task(int id, String name, Duration requiredTime) {
        this.id = id;
        this.name = name;
        this.requiredTime = requiredTime;
    }

    public Task(String name, Duration requiredTime) {
        this.name = name;
        this.requiredTime = requiredTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }

    public Task() {
        this.name = "New task";
        this.requiredTime = Duration.ZERO;
        this.subtasks = new ArrayList<>();
        this.assignedUsers = new ArrayList<>();
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public void addSubtask(Task subtask) {
//        subtasks.add(subtask);
        subtask.setMilestoneId(milestoneId);
        subtask.setProjectId(this.projectId);
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

    @Override
    public String toString() {
        return "Task: " + name +
                " " + requiredTime
                + " " + subtasks.size() + " subtasks" +
                " " + assignedUsers.size() + " assigned users";
    }

    public int getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(int parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
