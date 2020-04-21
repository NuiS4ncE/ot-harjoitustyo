package teoskanta.ui;

import java.io.File;
import java.sql.SQLException;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
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
    private UserService userService;
    private TitleService titleService;
    private DBUserDao userDao;
    private DBTitleDao titleDao;
    private SceneSwitcherUi sceneSwitcherUi;

    @Override
    public void start(Stage primaryStage) {
        userDao = new DBUserDao();
        userService = new UserService(userDao);
        titleDao = new DBTitleDao();
        titleService = new TitleService(titleDao);
        sceneSwitcherUi = new SceneSwitcherUi(primaryStage);

        // check database exists
        userService.checkDatabase();
        titleService.checkDatabase();

        // login scene
        this.loginScene = sceneSwitcherUi.switchToLogin();

        // main scene

        // setup primary stage
        primaryStage.setTitle("Teoskanta");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        closeRequest(primaryStage);
    }

    public void closeRequest(Stage stage) {
        stage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(userService.getLoggedInUser());
            if (userService.getLoggedInUser() != null) {
                userService.logout();
            }
            System.out.println(userService.getLoggedInUser());
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
