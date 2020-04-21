package teoskanta.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collections;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.title.dao.TitleDao;
import teoskanta.user.dao.DBUserDao;

public class TitleListService {

    private DBTitleDao dbTitleDao;
    private UserService userService;
    private DBUserDao dbUserDao;
    private User user;

    public TitleListService(DBTitleDao dbtitleDao2) {
        this.dbTitleDao = dbtitleDao2;
    }

    public List<Title> getList() {
        userService = new UserService(dbUserDao);
        List<Title> titleList = new ArrayList<>();
        try {
            titleList = dbTitleDao.list(userService.getLoggedInUser().getId());
        } catch (Exception e) {
            System.out.println("Title list retrieval had problems: " + e);
        }
        return titleList;
    }

    public List<Title> getTitleListReversed() {
        List<Title> titleList = getList();
        Collections.sort(titleList);
        Collections.reverse(titleList);
        return titleList;
    }

    public ObservableList<Title> getObservableTitles() {
        ObservableList<Title> obsTitleList = FXCollections.observableArrayList();
        obsTitleList.addAll(getList());
        return obsTitleList;
    }

}
