package pmtool;

public class User{
    public enum role {
        Dev,
        Manager,
        Analyst
    }
    private String name;
    private Integer id;
    private role role;

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
