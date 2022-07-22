package pmtool;

public class User {
    public enum role {
        Dev,
        Manager,
        Analyst
    }
    static private Integer counter = 0;
    private String name;
    private final Integer id;
    private role role;


    public User(String name, User.role role) {
        this.name = name;
        this.id = counter;
        counter++;
        this.role = role;
    }


    public Integer getId() {
        return id;
    }

    public User.role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
