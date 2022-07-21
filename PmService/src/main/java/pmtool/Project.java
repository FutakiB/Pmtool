package pmtool;

import java.time.LocalDateTime;
import java.util.List;
public class Project{
    private int id;
    private String name;
    private ProjectStatus status;
    private LocalDateTime startTime;
    private String clientName;
    private String backOfficeName;
    private List<Milestone> milestones;

    public Project(int id, String name, ProjectStatus status, LocalDateTime startTime, String clientName, String backOfficeName) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.startTime = startTime;
        this.clientName = clientName;
        this.backOfficeName = backOfficeName;
    }

    public void addMilestone(Milestone milestone) {
        milestones.add(milestone);
    }

    public void removeMilestone(Task milestone){
        milestones.remove(milestone);
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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBackOfficeName() {
        return backOfficeName;
    }

    public void setBackOfficeName(String backOfficeName) {
        this.backOfficeName = backOfficeName;
    }
}
