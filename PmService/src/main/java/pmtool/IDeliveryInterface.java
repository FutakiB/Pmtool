package pmtool;

public interface IDeliveryInterface {
    Delivery crateDelivery(Integer id, String name, DeliveryType type);
    void addToMilestone(Milestone milestone);
    void deliver();
}
