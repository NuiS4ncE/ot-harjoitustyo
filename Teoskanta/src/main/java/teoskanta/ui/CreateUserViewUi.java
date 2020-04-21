package teoskanta.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import teoskanta.domain.TitleService;
import teoskanta.domain.UserService;
import teoskanta.title.dao.DBTitleDao;
import teoskanta.user.dao.DBUserDao;

public class CreateUserViewUi {

    private Scene newUserScene;
    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService UserService;
    private TitleService TitleService;
    private Scene titleScene;
    private VBox titleNodes;
    private DBUserDao userDao;
    private DBTitleDao titleDao;

    public CreateUserViewUi() {

    }

    public Scene createUser(Label loginMessage, Stage primaryStage, Label loginErrorMessage) {
        loginErrorMessage.setText("");
        // create new user scene
        VBox createUserPane = new VBox(10);

        HBox createUsernamePane = new HBox(10);
        createUsernamePane.setPadding(new Insets(10));
        TextField createUsernameInput = new TextField();
        Label createUsernameLabel = new Label("username");
        //Label createPasswordLabel = new Label("password");
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
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = createUsernameInput.getText();
            String password = newPasswordInput.getText();

            if (username.length() == 2 || password.length() < 2) {
                userCreationMessage.setText("username or password too short");
                userCreationMessage.setTextFill(Color.RED);
            } else if (UserService.newUser(username, password)) {
                userCreationMessage.setText("");
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }

        });

        createUserPane.getChildren().addAll(userCreationMessage, createUsernamePane, newNamePane, createNewUserButton);

        return newUserScene = new Scene(createUserPane, 600, 250);

    }
}
