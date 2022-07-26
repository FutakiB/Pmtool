package pmtool;

//Leszállítandó
public class Delivery {
    static private Integer counter = 0;
    private Integer id;
    private Integer milestoneId;
    private String name;
    private DeliveryType type;

    private DeliveryStatus status;

    public Delivery(String name, DeliveryType type, Integer milestoneID) {
        this.id = counter;
        counter++;
        this.name = name;
        this.type = type;
        this.milestoneId = milestoneID;
    }

    public Delivery(Integer id, Integer milestoneID, String name, DeliveryType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.milestoneId = milestoneID;
    }

    public Delivery() {
        this.id = counter;
        counter++;
        this.milestoneId = null;
        this.name = "New delivery";
        this.type = DeliveryType.ARTIFACT;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Integer getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Integer milestoneId) {
        this.milestoneId = milestoneId;
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

    public DeliveryType getType() {
        return type;
    }

    public void setType(DeliveryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Delivery{" + "id=" + id + ", name=" + name + ", type=" + type + '}';
    }
}
