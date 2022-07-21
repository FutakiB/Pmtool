package pmtool;

import java.util.List;

public class PmService {
    private final InMemoryProjectRepository projectRepository;
    private final InMemoryMilestoneRepository milestoneRepository;
    private final InMemoryDeliveryRepository deliveryRepository;
    private final InMemoryTaskRepository taskRepository;


    public PmService(InMemoryMilestoneRepository milestoneRepository,
                     InMemoryDeliveryRepository deliveryRepository,
                     InMemoryTaskRepository taskRepository,
                     InMemoryProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.deliveryRepository = deliveryRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void addMilestone(Project project) {

    }

    public void addDelivery(Milestone milestone) {

    }

    public void addTask(Milestone milestone) {

    }


}
