package teoskanta.domain;

import java.util.*;
import teoskanta.user.dao.DBUserDao;

public class UserService {

    private DBUserDao userDao;
    private User loggedIn;

    public UserService(DBUserDao userDao) {
        this.userDao = userDao;
    }
    
    public boolean newUser(String username, String password){
        System.out.println(username + " " + password);
        try{
            userDao.create(new User(username, password));
            if(userDao.findUser(username, password)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            System.out.println("User creation had problems: " + e);
        }
        return false;
    }

    public boolean login(String username, String password) {
        System.out.println(username + " " + password);
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
