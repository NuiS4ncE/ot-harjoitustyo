package teoskanta.user;

/**
 * User class for user creation.
 *
 */
public class User {

    private String username;
    private String password;
    private int id;
    
    /**
     * Constructor for class.
     * @param username String-type variable for username input
     * @param password String-type variable for password input
     */
    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }
    
    /**
     * Constructor for class.
     * @param id int-type variable for user id input
     * @param username String-type variable for username input
     * @param password String-type variable for password input
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    /**
     * Empty constructor.
     */
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
