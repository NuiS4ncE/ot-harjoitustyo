package teoskanta.dao;

import java.io.File;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import teoskanta.title.Title;
import teoskanta.title.TitleService;
import teoskanta.user.User;
import teoskanta.user.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;

public class UserServiceTest {

    DBUserDao userDao;
    DBTitleDao DBTitleDao;
    UserService userService;
    TitleService titleService;

    @Before
    public void setUp() {
        userDao = new DBUserDao();
        DBTitleDao = new DBTitleDao();
        userService = new UserService(userDao);
        titleService = new TitleService(DBTitleDao);
        try {
            userService.checkDatabase();
            titleService.checkDatabase();
        } catch (Exception e) {
            System.out.println("Database creation in test failed: " + e);
        }
    }

    @Test
    public void checkLogout() throws Exception {
        User user = new User(0, "testis", "12343is");
        userService.newUser(user.getUsername(), user.getPassword());
        userService.login(user.getUsername(), user.getPassword());
        assertTrue(userService.logout());
        assertNull(userService.getLoggedInUser());
    }

    @AfterClass
    public static void deleteDB() {
        File file = new File("database.db");
        file.delete();
    }
}
