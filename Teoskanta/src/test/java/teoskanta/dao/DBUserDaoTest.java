package teoskanta.dao;

import java.io.File;
import org.junit.AfterClass;
import teoskanta.user.dao.DBUserDao;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import teoskanta.title.Title;
import teoskanta.user.User;
import teoskanta.user.UserService;
import teoskanta.title.dao.DBTitleDao;

public class DBUserDaoTest {

    DBUserDao dbUserDao;
    DBTitleDao dbTitleDao;
    UserService userService;

    @Before
    public void setUp() {
        dbUserDao = new DBUserDao();
        dbTitleDao = new DBTitleDao();
        userService = new UserService(dbUserDao);
        try {
            dbUserDao.checkDBFile();
            dbTitleDao.checkDBForTitles();
        } catch (Exception e) {
            System.out.println("Database creation in test failed: " + e);
        }
    }

    @Test
    public void createUserAndFindIt() throws Exception {
        dbUserDao.create(new User("test", "1234"));
        assertTrue(dbUserDao.findUser("test", "1234"));
    }

    @AfterClass
    public static void deleteDB() {
        File file = new File("database.db");
        file.delete();
    }

}
