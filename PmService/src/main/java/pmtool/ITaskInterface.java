package pmtool;

import java.util.List;

public interface ITaskInterface {
    public Task createTask(String name, int requiredDays);
    public Task getTask(int id);
    public List<Task> getAllTasks();
    public void addSubtask(Task task, Task subtask);
    public void removeSubtask(Task task, Task subtask);
    public List<Task> getSubtasks(Task task);
}
