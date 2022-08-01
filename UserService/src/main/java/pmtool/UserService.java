package pmtool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserManagement {
    private InMemoryUserRepository userRepository;

    @Autowired
    public UserService(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> get(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findAll().stream().filter(user -> user.getName() == name).toList();
    }

    @Override
    public List<User> getUsersByRole(User.Role role) {
        return userRepository.findAll().stream().filter(user -> user.getRole() == role).toList();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
