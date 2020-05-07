package teoskanta.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import teoskanta.title.TitleService;
import teoskanta.user.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;

/**
 * Class to create the main view for graphical user interface
 */
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
    private String loginTitle = "Teoskanta - Login";

    /**
     * Constructor for main view ui class
     *
     * @param primStage Stage-type variable
     */
    public MainViewUi(Stage primStage) {
        this.primaryStage = primStage;
    }

    //This is DEPRECATED
    /*
    * Method to build the main view ui scene
     */
    public Scene buildScene(String stageTitle) {
        primaryStage.setTitle(stageTitle);
        userDao = new DBUserDao();
        userService = new UserService(userDao);
        sceneSwitcherUi = new SceneSwitcherUi(primaryStage);
        titleDao = new DBTitleDao();
        titleService = new TitleService(titleDao);
        ScrollPane titleScrollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(titleScrollbar);

        HBox menuPane = new HBox(10);
        HBox logoutPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button createCategory = new Button("create");
        Button deleteCategory = new Button("delete");
        HBox createForm = new HBox(10);
        TextField newCategoryInput = new TextField();
        Button logoutButton = new Button("logout");
        logoutPane.getChildren().addAll(logoutButton);
        Label buttonErrorMessage = new Label();

        createForm.getChildren().addAll(newCategoryInput, createCategory, deleteCategory);

        createCategory.setOnAction(e -> {
            if (!newCategoryInput.getText().equals("")) {
                Button newButton = createButton(newCategoryInput.getText());
                newButton.setAlignment(Pos.CENTER_RIGHT);
                menuPane.getChildren().addAll(newButton);
                newCategoryInput.setText("");
                sceneSwitcherUi.SwitchToMain(stageTitle);
            } else {
                buttonErrorMessage.setText("Please write a category name");
                buttonErrorMessage.setTextFill(Color.RED);
                Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), (ActionEvent event) -> {
                    buttonErrorMessage.setText("");
                }));
                fiveSecondsWonder.play();
            }

        });
        
        menuPane.getChildren().addAll(menuLabel, buttonErrorMessage);
        logoutButton.setOnAction(e -> {
            userService.logout();
            primaryStage.setScene(sceneSwitcherUi.switchToLogin(loginTitle));
        });
        mainPane.setTop(logoutPane);
        mainPane.setCenter(menuPane);
        mainPane.setBottom(createForm);
        titleScene = new Scene(mainPane, 600, 600);

        return titleScene;
    }

    public Button createButton(String name) {
        Button categoryButton = new Button(name);
        String categoryname = name;
        titleDao = new DBTitleDao();
        titleService = new TitleService(titleDao);

        return categoryButton;
    }
}
