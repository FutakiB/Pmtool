package pmtool;

public interface IDeliveryInterface {
    Delivery crateDelivery(int id, String name, DeliveryType type);
    void addToMilestone(Milestone milestone);
    void deliver();
}
