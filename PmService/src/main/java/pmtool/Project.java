package pmtool;

import java.time.LocalDateTime;
import java.util.List;

public class Project {
    private Integer id;
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

    public Project() {
        this.id = 0;
        this.name = "New project";
        this.status = ProjectStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
        this.clientName = "";
        this.backOfficeName = "";
//        milestones = new ArrayList<>();
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public void addMilestone(Milestone milestone) {
//        milestones.add(milestone);
        milestone.setProjectId(id);
    }

    public void removeMilestone(Milestone milestone) {
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

    @Override
    public String toString() {
        return "Project{"
                + "id=" + id == null ? "null" : id + ", " + System.lineSeparator()
                + ", name=" + name + System.lineSeparator()
                + ", status=" + status + System.lineSeparator()
                + ", startTime=" + startTime + System.lineSeparator()
                + ", clientName=" + clientName + System.lineSeparator()
                + ", backOfficeName=" + milestones.toString() + System.lineSeparator()
                + ", backOfficeName=" + backOfficeName + '}' + System.lineSeparator();
    }
}
