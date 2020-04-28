package teoskanta.user;

import java.sql.SQLException;
import java.util.*;
import teoskanta.user.dao.DBUserDao;

/**
 * Class for handling actions between the user interface and DBUserDao
 * 
 */
public class UserService {

    private DBUserDao dbUserDao;
    private User loggedIn;

    /**
     * Constructor for class
     * @param userDao DBUserDao-type variable to construct the class with Dao input
     */
    public UserService(DBUserDao userDao) {
        this.dbUserDao = userDao;
        loggedIn = new User();
    }
    
    /**
     * Method to create a new user
     * @param username String-type input username
     * @param password String-type input password
     * @return returns true if user creation was successful false if not
     */
    public boolean newUser(String username, String password) {
        System.out.println(username + " " + password);
        try {
            dbUserDao.create(new User(username, password));
            if (dbUserDao.findUser(username, password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("User creation had problems: " + e);
        }
        return false;
    }
    
    /**
     * Method to login by setting the username and password values for the username and checking db for user
     * @param username String-type variable username
     * @param password String-type variable password
     * @return returns true if user is found from db and false if not
     */
    public boolean login(String username, String password) {
        System.out.println(username + " " + password);
        System.out.println("Now we're in userService-class");
        int id;
        try {
            if (dbUserDao.findUser(username, password)) {
                id = dbUserDao.getUserIdFromDB(username, password);
                System.out.println("this is the id: " + id);
                loggedIn.setId(id);
                loggedIn.setUsername(username);
                loggedIn.setPassword(password);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Login doesn't work or username not found: " + e);
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Method to logout by setting User object to null
     * @return returns true if successful and false if not
     */
    public boolean logout() {
        loggedIn = null;
        if (loggedIn == null) {
            return true;
        }
        return false;
    }

    public User getLoggedInUser() {
        return loggedIn;
    }

    /**
     * Checks if database and user table exists
     */
    public void checkDatabase() {
        try {
            dbUserDao.checkDBFile();
        } catch (Exception e) {
            System.out.println("Database check or creation for users failed: " + e);
        }
    }

    public int getUserId() {
        int id = 0;
        System.out.println("This is the loggedin id: " + loggedIn.getId());
        id = loggedIn.getId();
        return id;
    }
}
