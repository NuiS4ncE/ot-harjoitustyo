package teoskanta.ui;

import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import teoskanta.title.TitleService;
import teoskanta.user.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;

/**
 * Class to create the user creation view for graphical user interface
 */
public class CreateUserViewUi {

    private Scene newUserScene;
    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService userService;
    private TitleService titleService;
    private Scene titleScene;
    private VBox titleNodes;
    private DBUserDao userDao;
    private DBTitleDao titleDao;
    private SceneSwitcherUi sceneSwitcherUi;
    private String loginTitle = "Teoskanta - Login";

    /**
     * Constructor for user creation ui class
     */
    public CreateUserViewUi() {

    }

    /**
     * Handles the ui side of user creation
     *
     * @param loginMessage Label-type variable
     * @param primaryStage Stage-type variable used for scene switching
     * @parma loginError Label-type variable for login error messages
     */
    public Scene createUser(Label loginMessage, Stage primaryStage, Label loginErrorMessage) throws SQLException {
        userDao = new DBUserDao();
        userService = new UserService(userDao);
        sceneSwitcherUi = new SceneSwitcherUi(primaryStage);
        loginErrorMessage.setText("");
        // create new user scene
        VBox createUserPane = new VBox(10);

        HBox createUsernamePane = new HBox(10);
        createUsernamePane.setPadding(new Insets(10));
        TextField createUsernameInput = new TextField();
        Label createUsernameLabel = new Label("username");
        createUsernameLabel.setPrefWidth(100);
        createUsernamePane.getChildren().addAll(createUsernameLabel, createUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newPasswordInput = new TextField();
        Label newPasswordLabel = new Label("password");
        newPasswordLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newPasswordLabel, newPasswordInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        Button backButton = new Button("back");
        createNewUserButton.setPadding(new Insets(10));
        backButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = createUsernameInput.getText();
            String password = newPasswordInput.getText();

            if (username.length() == 2 || password.length() < 3) {
                userCreationMessage.setText("username or password too short");
                userCreationMessage.setTextFill(Color.RED);
            } else if (userService.newUser(username, password)) {
                userCreationMessage.setText("");
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(sceneSwitcherUi.switchToLogin(loginTitle));
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }

        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(sceneSwitcherUi.switchToLogin(loginTitle));
        });

        createUserPane.getChildren().addAll(userCreationMessage, createUsernamePane, newNamePane, createNewUserButton, backButton);

        return newUserScene = new Scene(createUserPane, 600, 250);

    }

}
