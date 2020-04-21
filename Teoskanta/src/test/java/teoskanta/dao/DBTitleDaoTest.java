package teoskanta.dao;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;
import teoskanta.domain.Title;
import teoskanta.domain.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import teoskanta.domain.Title;
import teoskanta.domain.User;
import teoskanta.domain.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;


public class DBTitleDaoTest {
    
    DBUserDao userDao;
    DBTitleDao DBTitleDao;
    UserService userService;

    @Before
    public void setUp() {
        userDao = new DBUserDao();
        DBTitleDao = new DBTitleDao();
        userService = new UserService(userDao);
        try {
            userDao.checkDBFile();
            DBTitleDao.checkDBForTitles();
        } catch (Exception e) {
            System.out.println("Database creation in test failed: " + e);
        }
    }
    
    @Test
    public void createTitleAndFindIt() throws Exception {
        User user = new User("test", "1234");
        DBTitleDao.checkDBForTitles();
        userDao.create(user);
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testbook", "test", "1234", userService.getUserId());
        DBTitleDao.create(title, title.getUserId());
        assertTrue(DBTitleDao.findTitle(title, userService.getLoggedInUser().getId()));
    }
    
    @Test
    public void deleteTitle() throws Exception {
        Title title = new Title("testbook", "test", "1234", userService.getUserId());
        DBTitleDao.delete(title, userService.getUserId());
        assertFalse(DBTitleDao.findTitle(title, userService.getUserId()));
    }

    @AfterClass
    public static void deleteDB(){
        File file = new File("database.db");
        file.delete();
    }
}