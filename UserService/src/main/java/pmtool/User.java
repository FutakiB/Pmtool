package pmtool;

public class User{
    public enum role {
        Dev,
        Manager,
        Analyst
    }
    private String name;
    private int id;
    private role role;
}
