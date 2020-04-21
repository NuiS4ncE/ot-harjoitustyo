package teoskanta.domain;

import java.sql.SQLException;
import java.util.*;
import teoskanta.user.dao.DBUserDao;

public class UserService {

    private DBUserDao dbUserDao;
    private User loggedIn;

    public UserService(DBUserDao userDao) {
        this.dbUserDao = userDao;
        loggedIn = new User();
    }

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
