package teoskanta.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import teoskanta.title.TitleService;
import teoskanta.user.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;


/*
* Class to create the login view for graphical user interface
 */
public class LoginViewUi {

    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService userService;
    private TitleService titleService;
    private DBUserDao userDao;
    private DBTitleDao titleDao;
    private Stage primaryStage;
    private CreateUserViewUi createUserView;
    private SceneSwitcherUi sceneSwitcherUi;
    private String mainTitle = "Teoskanta - Main";

    /**
     * Constructor for login ui
     */
    public LoginViewUi(Stage primStage) {
        this.primaryStage = primStage;
    }

    /**
     * Method to build the login ui scene
     */
    public Scene buildScene(String stageTitle) {
        primaryStage.setTitle(stageTitle);
        userDao = new DBUserDao();
        titleDao = new DBTitleDao();
        titleService = new TitleService(titleDao);
        userService = new UserService(userDao);
        sceneSwitcherUi = new SceneSwitcherUi(primaryStage);
        createUserView = new CreateUserViewUi();
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        loginLabel.setAlignment(Pos.TOP_RIGHT);
        TextField usernameInput = new TextField();
        usernameInput.setAlignment(Pos.TOP_CENTER);
        Label loginLabel2 = new Label("password");
        
        TextField passwordInput = new TextField();
        passwordInput.setAlignment(Pos.TOP_CENTER);

        inputPane.getChildren().addAll(loginLabel, usernameInput, loginLabel2, passwordInput);
        Label loginMessage = new Label();
        Label loginErrorMessage = new Label();

        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (userService.login(username, password)) {
                System.out.println(username + " " + password + " " + userService.login(username, password));
                loginMessage.setText("");
                //primaryStage.setScene(sceneSwitcherUi.SwitchToMain(mainTitle));
                primaryStage.setScene(sceneSwitcherUi.switchToTableView("Table"));
                usernameInput.setText("");
                passwordInput.setText("");
                titleService.checkDatabase();
            } else {
                loginErrorMessage.setText("username or password doesn't exist");
                loginErrorMessage.setTextFill(Color.RED);
                Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), (ActionEvent event) -> {
                    loginErrorMessage.setText("");
                }));
                fiveSecondsWonder.play();
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            try {
                primaryStage.setScene(createUserView.createUser(loginMessage, primaryStage, loginErrorMessage));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        loginPane.getChildren().addAll(loginErrorMessage, loginMessage, inputPane, loginButton, createButton);

        return loginScene = new Scene(loginPane, 600, 250);

    }
}
