package pmtool;

import java.time.LocalDateTime;
import java.util.List;

public interface IProjectInterface {
    Project createProject(int id, String name, ProjectStatus status, LocalDateTime startTime, String clientName, String backOfficeName, List<Task> milestones);
    void addTask(Task task);
    void modifyState(ProjectStatus status);

    void modifyStartTime(LocalDateTime startTime);

    List<Task> getMilestone();
    void deleteProject(int id);



}
