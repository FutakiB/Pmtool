package pmtool;

//Leszállítandó
public class Delivery {
    private Integer id;
    private String name;
    private DeliveryType type;
    private Integer milestoneID;

    public Delivery(Integer id, String name, DeliveryType type, Integer milestoneID) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.milestoneID = milestoneID;
    }

    public Delivery() {
        this.name = "New delivery";
        this.type = DeliveryType.ARTIFACT;
    }

    public int getId() {
        return id;
    }
    public Integer getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(Integer milestoneID) {
        this.milestoneID = milestoneID;
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
