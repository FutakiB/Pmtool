package pmtool;

import java.util.List;
import java.util.Optional;

public interface UserManagement {
    List<User> getAll();

    Optional<User> get(int id);

    public List<User> getUsersByName(String name);

    public List<User> getUsersByRole(User.role role);

    User save(User user);

    void delete(User user);

    void delete(int id);
}
