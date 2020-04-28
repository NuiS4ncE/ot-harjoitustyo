package teoskanta.dao;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;
import teoskanta.title.Title;
import teoskanta.user.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import teoskanta.title.Title;
import teoskanta.user.User;
import teoskanta.user.UserService;
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
        userDao.create(user);
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testbook", "test", "1234", userService.getUserId());
        DBTitleDao.create(title, title.getUserId());
        assertTrue(DBTitleDao.findTitle(title, userService.getLoggedInUser().getId()));
    }

    @Test
    public void deleteTitle() throws Exception {
        Title title = new Title("testbookzx", "testxz", "1234zx", userService.getUserId());
        DBTitleDao.delete(title, userService.getUserId());
        assertFalse(DBTitleDao.findTitle(title, userService.getUserId()));
    }

    @Test
    public void findTitle() throws Exception {
        User user = new User("testink", "12344141");
        userDao.create(user);
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testbookof", "testof", "1234of", userService.getUserId());
        DBTitleDao.create(title, title.getUserId());
        assertTrue(DBTitleDao.findTitle(title));
    }

    @Test
    public void makeList() throws Exception {
        User user = new User("testef", "1234efef");
        userDao.create(user);
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testbookas", "testas", "1234as", userService.getUserId());        
        DBTitleDao.create(title, userService.getUserId());
        List<Title> actual = Arrays.asList(title);
        //assertThat(actual, is(DBTitleDao.list(userService.getUserId())));
        assertThat(DBTitleDao.list(userService.getUserId()), not(IsEmptyCollection.empty()));
    }
    
    @Test
    public void checkHowReadDoesNothing() throws Exception {
        User user = new User("test2112", "12213234");
        userDao.create(user);
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testbookdads", "tsdsadest", "asdsa1234", userService.getUserId());
        DBTitleDao.create(title, title.getUserId());
        assertNull(DBTitleDao.read(title, title.getUserId()));
    }
    
    @Test
    public void checkHowReadDoesNothingAgain() throws Exception {
        User user = new User("test2112ds", "sda12213234");
        userDao.create(user);
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testbdsdsookdads", "sadsatsdsadest", "sadasdsa1234", userService.getUserId());
        DBTitleDao.create(title, userService.getUserId());
        assertNull(DBTitleDao.read(title, title.getUserId()));
        
        assertNull(DBTitleDao.read(title, Integer.SIZE));
    }


    @AfterClass
    public static void deleteDB() {
        File file = new File("database.db");
        file.delete();
    }
}
