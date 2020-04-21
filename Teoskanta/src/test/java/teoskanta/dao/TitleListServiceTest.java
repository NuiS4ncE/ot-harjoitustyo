package teoskanta.dao;

import java.io.File;
import java.sql.SQLException;
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
import teoskanta.domain.Title;
import teoskanta.domain.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import teoskanta.domain.Title;
import teoskanta.domain.TitleListService;
import teoskanta.domain.TitleService;
import teoskanta.domain.User;
import teoskanta.domain.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;

public class TitleListServiceTest {

    DBUserDao userDao;
    DBTitleDao DBTitleDao;
    UserService userService;
    TitleService titleService;
    TitleListService titleListService;

    @Before
    public void setUp() {
        userDao = new DBUserDao();
        DBTitleDao = new DBTitleDao();
        userService = new UserService(userDao);
        titleService = new TitleService(DBTitleDao);
        titleListService = new TitleListService(DBTitleDao);
        try {
            userService.checkDatabase();
            titleService.checkDatabase();
        } catch (Exception e) {
            System.out.println("Database creation in test failed: " + e);
        }
    }

    @Test
    public void listEmpty() throws SQLException {       
        assertThat(titleListService.getList(), IsEmptyCollection.empty());
    }
    
    @Test
    public void observabeTitles() throws Exception {
        assertThat(titleListService.getObservableTitles(), IsEmptyCollection.empty());
    }
    
    @Test
    public void getReversedList() throws Exception {
        assertThat(titleListService.getTitleListReversed(), IsEmptyCollection.empty());
        
    }

    @AfterClass
    public static void deleteDB() {
        File file = new File("database.db");
        file.delete();
    }
}
