package pmtool;

public class Main {
    public static void main(String[] args) {
        InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();
        InMemoryMilestoneRepository milestoneRepository = new InMemoryMilestoneRepository();
        InMemoryDeliveryRepository deliveryRepository = new InMemoryDeliveryRepository();
        InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();

        PmService pmService = new PmService(milestoneRepository, deliveryRepository, taskRepository, projectRepository);

        Project project = new Project();
        pmService.addProject(project);
        Milestone milestone = new Milestone();
        pmService.addMilestone(project, milestone);
        Delivery delivery = new Delivery();
        pmService.addDelivery(milestone, delivery);
        Task task = new Task();
        pmService.addTask(milestone, task);
        Task subtask = new Task();
        pmService.addTask(task, subtask);

        System.out.println(pmService.getAllMilestonesByProject(project));

//        System.out.println("Project added: " + project);
    }


}