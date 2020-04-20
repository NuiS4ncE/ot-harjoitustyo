package teoskanta.ui;

import java.io.File;
import java.sql.SQLException;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;
import teoskanta.domain.TitleService;
import teoskanta.domain.UserService;
import teoskanta.user.dao.DBUserDao;
import teoskanta.domain.Title;
import teoskanta.title.dao.DBTitleDao;

public class TeoskantaUi extends Application {

    private Scene newUserScene;
    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService UserService;
    private TitleService TitleService;
    private Scene titleScene;
    private VBox titleNodes;
    private DBUserDao userDao;
    private DBTitleDao titleDao;

    private void redrawTitlelist() {
        /*    titleNodes.getChildren().clear();
        
        List<Title> listedTitles = TitleService.getList();
        listedTitles.forEach(title->{
            titleNodes.getChildren().add(createTitleNode(title));
        });    
        //to be continued.. or edited.. or deleted..*/
    }

    public Node createTitleNode(Title title) {
        HBox box = new HBox(10);
        Label label = new Label(title.getName());
        Label label2 = new Label(title.getAuthor());
        label.setMinHeight(28);
        //Button button = new Button("done");
        //button.setOnAction(e->{
        //  titleService.markDone(title.getId());
        // redrawTodolist();
        //});

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));

        box.getChildren().addAll(label, spacer, label2);
        return box;
    }

    private void createUser(Label loginMessage, Stage primaryStage, Label loginErrorMessage) {
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

        newUserScene = new Scene(createUserPane, 600, 250);

    }

    @Override
    public void start(Stage primaryStage) {
        userDao = new DBUserDao();
        UserService = new UserService(userDao);
        titleDao = new DBTitleDao();
        TitleService = new TitleService(titleDao);

        // check database exists
        UserService.checkDatabase();
        TitleService.checkDatabase();
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
        Timer timer = new Timer();

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            //menuLabel.setText(username + " logged in");
            if (UserService.login(username, password)) {
                loginMessage.setText("");
                redrawTitlelist();
                primaryStage.setScene(titleScene);
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
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(loginErrorMessage, loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 600, 250);

        createUser(loginMessage, primaryStage, loginErrorMessage);

        // main scene
        ScrollPane titleScrollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(titleScrollbar);
        titleScene = new Scene(mainPane, 600, 600);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e -> {
            UserService.logout();
            primaryStage.setScene(loginScene);
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
        redrawTitlelist();

        titleScrollbar.setContent(titleNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        createTitle.setOnAction(e -> {
            TitleService.createTitle(newTitleInput.getText(), newAuthorInput.getText(), newYearInput.getText());
            newTitleInput.setText("");
            newAuthorInput.setText("");
            newYearInput.setText("");
            redrawTitlelist();
        });

        // setup primary stage
        primaryStage.setTitle("Teoskanta");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        closeRequest(primaryStage);
    }

    public void closeRequest(Stage stage) {
        stage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(UserService.getLoggedInUser());
            if (UserService.getLoggedInUser() != null) {
                UserService.logout();
            }
            System.out.println(UserService.getLoggedInUser());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
