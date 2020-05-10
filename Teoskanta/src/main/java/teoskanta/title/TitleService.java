package teoskanta.title;

import teoskanta.title.Title;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import teoskanta.user.dao.DBUserDao;
import teoskanta.user.User;
import teoskanta.user.User;
import teoskanta.user.UserService;
import teoskanta.title.dao.DBTitleDao;

/**
 * Class to handle different title actions between ui and Dao.
 *
 */
public class TitleService {

    private DBUserDao dbUserDao;
    private User user;
    private DBTitleDao dbTitleDao;
    private UserService userService;
    private Title title;
    private TitleService titleService;

    /**
     * Constructor for class.
     *
     * @param dbtitleDao DBTitleDao-type variable to construct the class with
     * Dao class input
     */
    public TitleService(DBTitleDao dbtitleDao) {
        this.dbTitleDao = dbtitleDao;
    }

    /**
     * Method to create a title.
     *
     * @param name String-type input name
     * @param author String-type input author
     * @param year String-type input year
     * @param category String-type variable for category input
     * @return returns true if after creation can find title from db
     */
    public boolean createTitle(String name, String author, String year, String category) {
        dbUserDao = new DBUserDao();
        userService = new UserService(dbUserDao);
        System.out.println(name + " " + author + " " + year + " " + userService.getUserId() + " " + category);
        title = new Title(name, author, year, category, userService.getUserId());
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

    /**
     * Method to delete title.
     *
     * @param title Title-type variable to delete the title
     * @return returns true if title was deleted successfully and false if not
     */
    public boolean deleteTitle(Title title) {
        dbUserDao = new DBUserDao();
        userService = new UserService(dbUserDao);
        int userid;
        userid = userService.getLoggedInUser().getId();
        //System.out.println("USERID IS: " + userid + " AND " + userService.getUserId());
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

    /**
     * Method to check that database exists.
     */
    public void checkDatabase() {
        try {
            dbTitleDao.checkDBForTitles();
        } catch (Exception e) {
            System.out.println("Database check or creation for titles failed: " + e);
        }
    }

}
