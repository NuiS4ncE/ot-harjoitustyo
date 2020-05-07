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


public class TitleServiceTest {
    
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
    public void createTitle(){
        User user = new User("test2", "12342");
        userService.newUser(user.getUsername(), user.getPassword());
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testing", "tester", "1234", "erarrer", userService.getLoggedInUser().getId());
        assertTrue(titleService.createTitle(title.getName(), title.getAuthor(), title.getYear(), title.getCategory()));        
    }
    
    @Test
    public void deleteTitle(){
        User user = new User("test3", "12343");
        userService.newUser(user.getUsername(), user.getPassword());
        userService.login(user.getUsername(), user.getPassword());
        Title title = new Title("testing2", "tester2", "12342", "tesstst",userService.getUserId());
        titleService.createTitle(title.getName(), title.getAuthor(), title.getYear(), title.getCategory());
        assertFalse(titleService.deleteTitle(title));
    }
    
    @AfterClass
    public static void deleteDB() {
        File file = new File("database.db");
        file.delete();
    }

}