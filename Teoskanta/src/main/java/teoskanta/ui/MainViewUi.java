package teoskanta.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teoskanta.domain.TitleService;
import teoskanta.domain.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;

public class MainViewUi {

    private Scene newUserScene;
    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService userService;
    private TitleService titleService;
    private Scene titleScene;
    private VBox titleNodes;
    private DBUserDao userDao;
    private DBTitleDao titleDao;
    private Stage primaryStage;
    private SceneSwitcherUi sceneSwitcherUi;

    public MainViewUi(Stage primStage) {
        this.primaryStage = primStage;
    }
/*
    public Scene buildScene() {
        userDao = new DBUserDao();
        userService = new UserService(userDao);
        sceneSwitcherUi = new SceneSwitcherUi(primaryStage);
        titleDao = new DBTitleDao();
        titleService = new TitleService(titleDao);
        ScrollPane titleScrollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(titleScrollbar);
        titleScene = new Scene(mainPane, 600, 600);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e -> {
            userService.logout();
            primaryStage.setScene(sceneSwitcherUi.SwitchToLogin());
        });

        HBox createForm = new HBox(10);
        Button createTitle = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newTitleInput = new TextField();
        TextField newAuthorInput = new TextField();
        TextField newYearInput = new TextField();
        createForm.getChildren().addAll(newTitleInput, newAuthorInput, newYearInput, spacer, createTitle);

        titleNodes = new VBox(10);
        titleNodes.setMaxWidth(360);
        titleNodes.setMinWidth(360);
        //redrawTitlelist();

        titleScrollbar.setContent(titleNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        createTitle.setOnAction(e -> {
            titleService.createTitle(newTitleInput.getText(), newAuthorInput.getText(), newYearInput.getText());
            newTitleInput.setText("");
            newAuthorInput.setText("");
            newYearInput.setText("");
            //redrawTitlelist();
        });
        return titleScene;
    } */
}
