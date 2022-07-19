package pmtool;

import java.util.List;

public class PmService {
    private final InMemoryProjectRepository projectRepository;
    private final InMemoryMilestoneRepository milestoneRepository;
    private final InMemoryDeliveryRepository deliveryRepository;
    private final InMemoryTaskRepository taskRepository;


    public PmService() {
        projectRepository = new InMemoryProjectRepository();
        milestoneRepository = new InMemoryMilestoneRepository();
        deliveryRepository = new InMemoryDeliveryRepository();
        taskRepository = new InMemoryTaskRepository();
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


}
