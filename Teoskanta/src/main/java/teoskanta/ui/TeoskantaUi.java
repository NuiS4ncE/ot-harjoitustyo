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
    private MainViewUi mainViewUi;
    private LoginViewUi loginViewUi;

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

    @Override
    public void start(Stage primaryStage) {
        userDao = new DBUserDao();
        UserService = new UserService(userDao);
        titleDao = new DBTitleDao();
        TitleService = new TitleService(titleDao);
        loginViewUi = new LoginViewUi(primaryStage);
        mainViewUi = new MainViewUi(primaryStage);

        // check database exists
        UserService.checkDatabase();
        TitleService.checkDatabase();

        // login scene
        this.loginScene = loginViewUi.buildScene();

        // main scene
        mainViewUi.buildScene();

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
