package teoskanta.ui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;
import javafx.scene.Scene;
import teoskanta.domain.Title;
import teoskanta.domain.TitleListService;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teoskanta.title.dao.DBTitleDao;

public class TableViewUi {

    private TableView<Title> titleTable;
    private TitleListService titleListService;
    private Stage primaryStage;
    private Scene tableViewScene;
    private DBTitleDao titleDao;

    public TableViewUi(Stage primStage) {
        this.primaryStage = primStage;
    }

    public Scene buildScene() {
        titleTable = new TableView<>();
        titleDao = new DBTitleDao();
        titleListService = new TitleListService(titleDao);

        TableColumn<Title, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Title, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Title, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        titleTable.setItems(titleListService.getObservableTitles());
        titleTable.getColumns().addAll(Arrays.asList(nameColumn, authorColumn, yearColumn));

        VBox vbox = new VBox(titleTable);

        tableViewScene = new Scene(vbox);

        return tableViewScene;
    }
}
