package pmtool;

import java.util.List;

public interface IMilestoneInterface {
    List<Milestone> getAll();
    Milestone get(int id);
    void add(Milestone milestone);
    void update(int id,Milestone milestone);
    void delete(int id);
}
