package pmtool;

public class Main {
    public static void main(String[] args) {

        System.out.println("PM Service Main!");

        InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();
        InMemoryMilestoneRepository milestoneRepository = new InMemoryMilestoneRepository();
        InMemoryDeliveryRepository deliveryRepository = new InMemoryDeliveryRepository();
        InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();

        PmService pmService = new PmService(milestoneRepository, deliveryRepository, taskRepository, projectRepository);

//        Project project = new Project(1, "Project 1");
    }


}