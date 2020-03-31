package teoskanta.domain;

import java.util.*;
import teoskanta.dao.UserDao;

public class UserService {

    private UserDao userDao;
    private User loggedIn;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(String username, String password) {
        System.out.println(username + password);
        System.out.println("Now we're in userService-method");
        try {
            if (userDao.findUser(username, password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Login doesn't work: " + e);
        }
        //if(user == null) {

        //}
        //loggedIn = user;
        return false;
    }
}
