package pmtool;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {
    public enum Role {
        DEV,
        MANAGER,
        ANALYST
    }

    @Id
    private Integer id;
    static private Integer counter = 0;
    private String name;
    private Role role;
    public User() {
    }

    public User(Integer id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User(String name, Role role) {
        this.id = counter;
        this.name = name;
        counter++;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
