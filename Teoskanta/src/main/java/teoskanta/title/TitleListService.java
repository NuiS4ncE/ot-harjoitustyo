package teoskanta.title;

import teoskanta.title.Title;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collections;
import teoskanta.user.User;
import teoskanta.user.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.title.dao.TitleDao;
import teoskanta.user.dao.DBUserDao;

/**
 * Class for getting title lists from titleDao
 *
 */
public class TitleListService {

    private DBTitleDao dbTitleDao;
    private UserService userService;
    private DBUserDao dbUserDao;
    private User user;

    /**
     * Constructor for class
     *
     * @param dbtitleDao2 DBTitleDao-type variable to construct the class with
     * Dao class input
     */
    public TitleListService(DBTitleDao dbtitleDao2) {
        this.dbTitleDao = dbtitleDao2;
    }

    /**
     * Gets the list from DBUserDao
     *
     * @return returns list of titles
     */
    public List<Title> getList() {
        userService = new UserService(dbUserDao);
        List<Title> titleList = new ArrayList<>();
        try {
            titleList = dbTitleDao.list(userService.getUserId());
        } catch (Exception e) {
            System.out.println("Title list retrieval had problems: " + e);
        }
        return titleList;
    }

    /**
     * Gets the list of titles from DBUserDao and reverses it
     *
     * @return returns reversed list of titles
     */
    public List<Title> getTitleListReversed() {
        List<Title> titleList = getList();
        Collections.sort(titleList);
        Collections.reverse(titleList);
        return titleList;
    }

    /**
     * Gets a list of titles from DBTitleDao and makes it into a
     * ObservableList-type list
     *
     * @return returns ObservableList-type variable
     */
    public ObservableList<Title> getObservableTitles() {
        ObservableList<Title> obsTitleList = FXCollections.observableArrayList();
        obsTitleList.addAll(getList());
        return obsTitleList;
    }

    public ObservableList<Title> getObservableTitles(String category) {
        ObservableList<Title> obsTitleList = FXCollections.observableArrayList();
        try {
            obsTitleList.addAll(dbTitleDao.getListByCategory(userService.getUserId(), category));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obsTitleList;
    }

    public ObservableList<String> getObservableCategories() {
        userService = new UserService(dbUserDao);
        List<String> categoryList = new ArrayList<>();
        try {
            categoryList = dbTitleDao.getCategoryList(userService.getUserId());
        } catch (Exception e) {
            System.out.println("Category list retrieval had problems: " + e);
        }

        return FXCollections.observableArrayList(categoryList);
    }

}
