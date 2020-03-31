package teoskanta.dao;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import teoskanta.domain.User;


public class UserDaoTest {
    
    UserDao userDao;
    
    @Before
    public void setUp() {
        userDao = new UserDao();
        try{
        userDao.checkDatabaseFile();
        }catch (Exception e) {
            System.out.println("Database creation in test failed: " + e);
        }
    }

    @Test
    public void createUserAndFindIt() throws Exception{
        userDao.create(new User("test", "1234"));
        assertTrue(userDao.findUser("test", "1234"));
        
    }
}
