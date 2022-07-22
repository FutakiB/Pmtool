package pmtool;

public class User {
    public enum Role {
        DEV,
        MANAGER,
        ANALYST
    }

    private String name;
    private Integer id;
    private Role role;

    public User(Integer id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User(String name, Role role) {
        this.id = null;
        this.name = name;
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
