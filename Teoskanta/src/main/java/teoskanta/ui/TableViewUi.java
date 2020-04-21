package teoskanta.ui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import teoskanta.domain.Title;
import teoskanta.domain.TitleListService;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teoskanta.domain.TitleService;
import teoskanta.title.dao.DBTitleDao;

public class TableViewUi {

    private TableView<Title> titleTable;
    private TitleListService titleListService;
    private Stage primaryStage;
    private Scene tableViewScene;
    private DBTitleDao titleDao;
    private BorderPane borderPane;
    private TitleService titleService;

    public TableViewUi(Stage primStage) {
        this.primaryStage = primStage;
    }

    public Scene buildScene() {
        borderPane = new BorderPane();
        titleTable = new TableView<>();
        titleDao = new DBTitleDao();
        titleListService = new TitleListService(titleDao);
        titleService = new TitleService(titleDao);

        TableColumn<Title, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Title, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Title, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        titleTable.setItems(titleListService.getObservableTitles());
        titleTable.getColumns().addAll(Arrays.asList(nameColumn, authorColumn, yearColumn));

        VBox vbox = new VBox(titleTable);
        HBox createForm = new HBox(10);
        Button createTitle = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newTitleInput = new TextField();
        TextField newAuthorInput = new TextField();
        TextField newYearInput = new TextField();
        createForm.getChildren().addAll(newTitleInput, newAuthorInput, newYearInput, spacer, createTitle);

        borderPane.setCenter(vbox);
        borderPane.setBottom(createForm);
        tableViewScene = new Scene(borderPane);
        
         createTitle.setOnAction(e -> {
            titleService.createTitle(newTitleInput.getText(), newAuthorInput.getText(), newYearInput.getText());
            newTitleInput.setText("");
            newAuthorInput.setText("");
            newYearInput.setText("");
            primaryStage.setScene(buildScene());
         });

        return tableViewScene;
    }

   
}
