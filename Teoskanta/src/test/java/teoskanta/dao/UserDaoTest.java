package teoskanta.dao;

import teoskanta.user.dao.DBUserDao;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import teoskanta.domain.Title;
import teoskanta.domain.User;
import teoskanta.domain.UserService;
import teoskanta.title.dao.DBTitleDao;

public class UserDaoTest {

    DBUserDao userDao;
    DBTitleDao titleDao;
    UserService userService;

    @Before
    public void setUp() {
        userDao = new DBUserDao();
        titleDao = new DBTitleDao();
        userService = new UserService(userDao);
        try {
            userDao.checkDatabaseFile();
            titleDao.checkDatabaseFileForTitles();
        } catch (Exception e) {
            System.out.println("Database creation in test failed: " + e);
        }
    }

    @Test
    public void createUserAndFindIt() throws Exception {
        userDao.create(new User("test", "1234"));
        assertTrue(userDao.findUser("test", "1234"));
    }

    @Test
    public void createTitleAndFindIt() throws Exception {
        User user = new User("test", "1234");
        titleDao.checkDatabaseFileForTitles();
        //userDao.create(user);
        userService.login(user.getUsername(), user.getPassword());
        titleDao.create(new Title("testbook", "test", "1234", userService.getLoggedInUser().getId()));
        assertTrue(titleDao.findTitle("testbook", "test", "1234", userService.getLoggedInUser().getId()));
    }
}
