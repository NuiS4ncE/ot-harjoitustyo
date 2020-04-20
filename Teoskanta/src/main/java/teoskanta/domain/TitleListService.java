package teoskanta.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collections;
import teoskanta.title.dao.TitleDao;

public class TitleListService {

    private TitleDao dbtitleDao;
    private UserService userService;

    public TitleListService(TitleDao DBtitleDao) {
        this.dbtitleDao = DBtitleDao;
    }

    public List<Title> getList() {
        List<Title> titleList = new ArrayList<>();
        try {
            titleList = dbtitleDao.list(userService.getUserId());
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
