package pmtool;

import java.util.List;

public interface IUserInterface {
    public User login(String username, String password);
    public User register(String username, String password, String role);
    public User getUser(int id);
    public List<User> getAllUsers();
}
