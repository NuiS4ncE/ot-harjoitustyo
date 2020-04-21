package teoskanta.domain;

public class User {

    private String username;
    private String password;
    private int id;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }

}
