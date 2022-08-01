package pmtool;

import java.time.Duration;
import java.util.List;

public class Task {
    private Integer id;
    private Integer projectId;
    private Integer milestoneId;
    private Integer parentTaskId;
    private String name;
    private Duration requiredTime;
    private List<Task> subtasks;
    private List<User> assignedUsers;
    private TaskStatus status;

    public Task(Integer id, Integer projectId, Integer milestoneId, Integer parentTaskId, String name, Duration requiredTime) {
        this.id = id;
        this.projectId = projectId;
        this.milestoneId = milestoneId;
        this.parentTaskId = parentTaskId;
        this.name = name;
        this.requiredTime = requiredTime;
    }

    public Task(int id, String name, Duration requiredTime) {
        this.id = id;
        this.name = name;
        this.requiredTime = requiredTime;
    }

    public Task(String name, Duration requiredTime) {
        this.name = name;
        this.requiredTime = requiredTime;
    }

    public Task() {
        this.id = 0;
        this.projectId = 0;
        this.milestoneId = 0;
        this.parentTaskId = 0;
        this.name = "New task";
        this.requiredTime = Duration.ZERO;
//        this.subtasks = new ArrayList<>();
//        this.assignedUsers = new ArrayList<>();
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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
        subtask.setParentTaskId(id);
    }

    private void removeSubtask(Task subtask) {
        subtasks.remove(subtask);
    }

    private void addAssignedUser(User assignedUser) {
        assignedUsers.add(assignedUser);
    }

    private void removeAssignedUser(User assignedUser) {
        assignedUsers.remove(assignedUser);
    }

    private List<Task> getSubtasks() {
        return subtasks;
    }

    private void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }

    public Integer getId() {
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

//    @Override
//    public String toString() {
//        return "Task: " + name +
//                " " + requiredTime+
//                + " " + subtasks.size() + " subtasks" +
//                " " + assignedUsers.size() + " assigned users";
//    }

    public Integer getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(int parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
