package teoskanta.ui;

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
import teoskanta.domain.TitleService;
import teoskanta.domain.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;

public class LoginViewUi {

    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService UserService;
    private TitleService TitleService;
    private Scene titleScene;
    private VBox titleNodes;
    private DBUserDao userDao;
    private DBTitleDao titleDao;
    private Stage primaryStage;
    private CreateUserViewUi createUserView;
    private SceneSwitcherUi sceneSwitcherUi;

    public LoginViewUi(Stage primStage) {
        this.primaryStage = primStage;
    }

    public Scene buildScene() { 
        userDao = new DBUserDao();
        titleDao = new DBTitleDao();
        TitleService = new TitleService(titleDao);
        UserService = new UserService(userDao);
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

        inputPane.getChildren().addAll(loginLabel, usernameInput, loginLabel2, passwordInput);
        Label loginMessage = new Label();
        Label loginErrorMessage = new Label();

        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            //menuLabel.setText(username + " logged in");
            if (UserService.login(username, password)) {
                System.out.println(username + " " + password + " " + UserService.login(username, password));
                loginMessage.setText("");
                primaryStage.setScene(sceneSwitcherUi.SwitchToMain());
                usernameInput.setText("");
                passwordInput.setText("");
                TitleService.checkDatabase();
            } else {
                loginErrorMessage.setText("username or password doesn't exist");
                loginErrorMessage.setTextFill(Color.RED);
                Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), (ActionEvent event) -> {
                    loginErrorMessage.setText("");
                }));
                //fiveSecondsWonder.setCycleCount(1);
                fiveSecondsWonder.play();
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            primaryStage.setScene(createUserView.createUser(loginMessage, primaryStage, loginErrorMessage));
        });
        
        loginPane.getChildren().addAll(loginErrorMessage, loginMessage, inputPane, loginButton, createButton);

        
        return loginScene = new Scene(loginPane, 600, 250);


    }
}
