package teoskanta.ui;

import java.io.File;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import teoskanta.domain.UserService;
import teoskanta.dao.UserDao;

public class TeoskantaUi extends Application {

    private Scene newUserScene;
    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService UserService;
    private Scene titleScene;
    private VBox titleNodes;
    private UserDao userDao;

    private void redrawTitlelist() {
        titleNodes.getChildren().clear();

        //to be continued.. or edited.. or deleted..
    }

    private void createUser(Label loginMessage, Stage primaryStage) {

        // create new user scene
        VBox createUserPane = new VBox(10);

        HBox createUsernamePane = new HBox(10);
        createUsernamePane.setPadding(new Insets(10));
        TextField createUsernameInput = new TextField();
        Label createUsernameLabel = new Label("username");
        Label createPasswordLabel = new Label("password");
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
                userCreationMessage.setText("username or name too short");
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

        newUserScene = new Scene(createUserPane, 600, 250);

    }

    @Override
    public void start(Stage primaryStage) {
        userDao = new UserDao();
        UserService = new UserService(userDao);

        // check database exists
        try {
            userDao.checkDatabaseFile();
        } catch (Exception e) {
            System.out.println("Database creation failed: " + e);
        }

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

        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            System.out.println(username);
            String password = passwordInput.getText();
            System.out.println(password);
            //menuLabel.setText(username + " logged in");
            if (UserService.login(username, password)) {
                loginMessage.setText("");
                redrawTitlelist();
                primaryStage.setScene(titleScene);
                usernameInput.setText("");
                passwordInput.setText("");
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 600, 250);

        createUser(loginMessage, primaryStage);

        // main scene
        ScrollPane todoScollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(todoScollbar);
        titleScene = new Scene(mainPane, 300, 250);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        /* logoutButton.setOnAction(e->{
            todoService.logout();
            primaryStage.setScene(loginScene);
        });        
         */
        HBox createForm = new HBox(10);
        Button createTodo = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newTodoInput = new TextField();
        createForm.getChildren().addAll(newTodoInput, spacer, createTodo);

        titleNodes = new VBox(10);
        titleNodes.setMaxWidth(360);
        titleNodes.setMinWidth(360);
        redrawTitlelist();

        //todoScollbar.setContent(todoNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        /* createTodo.setOnAction(e->{
            todoService.createTodo(newTodoInput.getText());
            newTodoInput.setText("");       
            redrawTodolist();
        });
         */
        // seutp primary stage
        primaryStage.setTitle("Teoskanta");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            //System.out.println(todoService.getLoggedUser());
            //if (todoService.getLoggedUser()!=null) {
            //   e.consume();   
            // }

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
