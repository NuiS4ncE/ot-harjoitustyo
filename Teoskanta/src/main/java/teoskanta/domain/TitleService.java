package teoskanta.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import teoskanta.user.dao.DBUserDao;
import teoskanta.domain.User;
import teoskanta.title.dao.DBTitleDao;

public class TitleService {

    private DBUserDao userDao;
    private User user;
    private DBTitleDao dbTitleDao;
    private UserService UserService;
    private Title title;

    public TitleService(DBTitleDao dbtitleDao) {
        this.dbTitleDao = dbtitleDao;
    }

    public boolean createTitle(String name, String author, String year) {
        System.out.println(name + " " + author + " " + year);
        title = new Title(name, author, year, 1);
        try {
            dbTitleDao.create(title);
            if (dbTitleDao.findTitle(name, author, year)) {
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

    public boolean deleteTitle(String name, String author, String year, int userid) {
        System.out.println(name + " " + author + " " + year);

        try {
            dbTitleDao.findTitle(name, author, year, userid);
            //dbTitleDao.delete(name, author, year, userid);
            if (!dbTitleDao.findTitle(name, author, year, userid)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Title creation had problems: " + e);
        }
        return false;
    }

    public List<Title> getList() {
        List<Title> titleList = new ArrayList<>();
        try {
            titleList = dbTitleDao.list(user.getId());
        } catch (Exception e) {
            System.out.println("Title list retrieval had problems: " + e);
        }
        return titleList;
    }

    public void checkDatabase() {
        try {
            dbTitleDao.checkDatabaseFileForTitles();
        } catch (Exception e) {
            System.out.println("Database check or creation for titles failed: " + e);
        }
    }
}
