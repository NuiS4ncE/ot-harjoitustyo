package teoskanta.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import teoskanta.user.dao.DBUserDao;
import teoskanta.domain.User;
import teoskanta.title.dao.DBTitleDao;

public class TitleService {

    private DBUserDao dbUserDao;
    private User user;
    private DBTitleDao dbTitleDao;
    private UserService userService;
    private Title title;
    private TitleService titleService;

    public TitleService(DBTitleDao dbtitleDao) {
        this.dbTitleDao = dbtitleDao;
    }

    public boolean createTitle(String name, String author, String year) {
        dbUserDao = new DBUserDao();
        userService = new UserService(dbUserDao);
        System.out.println(name + " " + author + " " + year + " " + userService.getUserId());
        title = new Title(name, author, year, userService.getUserId());
        try {
            dbTitleDao.create(title, title.getUserId());
            if (dbTitleDao.findTitle(title, title.getUserId())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Title creation had problems: " + e);
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTitle(Title title) {
        dbUserDao = new DBUserDao();
        userService = new UserService(dbUserDao);
        int userid;
        userid = userService.getLoggedInUser().getId();
        System.out.println("USERID IS: " + userid + " AND " + userService.getUserId());
        try {
            dbTitleDao.delete(title, userid);
            System.out.println(dbTitleDao.findTitle(title, userid));
            if (!dbTitleDao.findTitle(title, userid)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Title deletion had problems: " + e);
            e.printStackTrace();
        }
        return false;
    }

    public void checkDatabase() {
        try {
            dbTitleDao.checkDBForTitles();
        } catch (Exception e) {
            System.out.println("Database check or creation for titles failed: " + e);
        }
    }

}
