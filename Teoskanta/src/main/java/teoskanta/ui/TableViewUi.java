package teoskanta.ui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import teoskanta.title.Title;
import teoskanta.title.TitleListService;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import teoskanta.title.TitleService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.UserService;
import teoskanta.user.dao.DBUserDao;

/**
 * Class for table view graphical user interface creation
 */
public class TableViewUi {

    private TableView<Title> titleTable;
    private TitleListService titleListService;
    private Stage primaryStage;
    private Scene tableViewScene;
    private DBTitleDao dbTitleDao;
    private BorderPane borderPane;
    private TitleService titleService;
    private UserService userService;
    private SceneSwitcherUi sceneSwitcherUi;
    private DBUserDao dbUserDao;
    private String loginTitle = "Teoskanta - Login";

    /**
     * Constructor for listing tableview ui
     *
     * @param primStage Stage-type variable for scene set up
     */
    public TableViewUi(Stage primStage) {
        this.primaryStage = primStage;
    }

    /**
     * Method for building the ui scene
     */
    public Scene buildScene(String stageTitle) {
        primaryStage.setTitle(stageTitle);
        borderPane = new BorderPane();
        titleTable = new TableView<>();
        dbTitleDao = new DBTitleDao();
        dbUserDao = new DBUserDao();
        sceneSwitcherUi = new SceneSwitcherUi(primaryStage);
        userService = new UserService(dbUserDao);
        titleListService = new TitleListService(dbTitleDao);
        titleService = new TitleService(dbTitleDao);

        TableColumn<Title, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.prefWidthProperty().bind(titleTable.widthProperty().multiply(0.33));
        
        TableColumn<Title, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.prefWidthProperty().bind(titleTable.widthProperty().multiply(0.33));
        
        TableColumn<Title, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.prefWidthProperty().bind(titleTable.widthProperty().multiply(0.33));
        
        titleTable.setItems(titleListService.getObservableTitles());
        titleTable.getColumns().addAll(Arrays.asList(nameColumn, authorColumn, yearColumn));
        
        
        VBox vbox = new VBox(titleTable);
        HBox topPane = new HBox(10);
        HBox createForm = new HBox(10);
        createForm.setPadding(new Insets(10));
        Button createTitle = new Button("create");
        Button deleteTitle = new Button("delete");
        TextField newTitleInput = new TextField();
        newTitleInput.setPromptText("title");
        TextField newAuthorInput = new TextField();
        newAuthorInput.setPromptText("author");
        TextField newYearInput = new TextField();
        newYearInput.setPromptText("year");
        Button logoutButton = new Button("logout");
        createForm.getChildren().addAll(newTitleInput, newAuthorInput, newYearInput, comboBoxSetUp(), createTitle, deleteTitle);
        topPane.getChildren().addAll(logoutButton);
        
        borderPane.setTop(topPane);
        borderPane.setCenter(vbox);
        borderPane.setBottom(createForm);
        tableViewScene = new Scene(borderPane);

        createTitle.setOnAction(e -> {
            titleService.createTitle(newTitleInput.getText(), newAuthorInput.getText(), newYearInput.getText());
            newTitleInput.setText("");
            newAuthorInput.setText("");
            newYearInput.setText("");
            primaryStage.setScene(buildScene(stageTitle));
        });

        deleteTitle.setOnAction(e -> {
            Title selectedItem = titleTable.getSelectionModel().getSelectedItem();
            titleTable.getItems().remove(selectedItem);
            titleService.deleteTitle(selectedItem);
        });

        /*borderPane.setTop(logoutButton);
        borderPane.getChildren().addAll(logoutButton);
         */
        logoutButton.setOnAction(e -> {
            userService.logout();
            primaryStage.setScene(sceneSwitcherUi.switchToLogin(loginTitle));
        });

        return tableViewScene;
    }

    public ComboBox<String> comboBoxSetUp() {
        dbTitleDao = new DBTitleDao();
        titleListService = new TitleListService(dbTitleDao);
        ComboBox<String> chooseBox = new ComboBox<>(titleListService.getObservableCategories());
        chooseBox.autosize();
        chooseBox.setEditable(true);
        chooseBox.setPromptText("category");
        //chooseBox.setOnAction(e -> ));
        
        return chooseBox;
    }

}
