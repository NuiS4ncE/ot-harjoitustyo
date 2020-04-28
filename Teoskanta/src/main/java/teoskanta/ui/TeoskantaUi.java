package teoskanta.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import teoskanta.title.TitleService;
import teoskanta.user.UserService;
import teoskanta.user.dao.DBUserDao;
import teoskanta.title.dao.DBTitleDao;

/**
* Main graphical user interface class for the app
 */
public class TeoskantaUi extends Application {

    private Scene newUserScene;
    private Label menuLabel = new Label();
    private Scene loginScene;
    private UserService userService;
    private TitleService titleService;
    private DBUserDao userDao;
    private DBTitleDao titleDao;
    private SceneSwitcherUi sceneSwitcherUi;

    /**
    * Method to start the graphical user interface of the application 
    * @param primaryStage Stage-type variable to set the main stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
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

    /**
    * Method to check if the user is logged in and to clear the user object of values
    * @param stage Stage-type variable for setting the close request
     */
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
    /**
    * Main method to launch the app
    */
    public static void main(String[] args) {
        launch(args);
    }
}
