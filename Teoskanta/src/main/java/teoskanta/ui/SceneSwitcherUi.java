package teoskanta.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class to switch different scenes
 */
public class SceneSwitcherUi {

    private Stage primaryStage;
    private LoginViewUi loginViewUi;
    private TableViewUi tableViewUi;

    /**
     * Constructor for ui scene switcher class
     *
     * @param primStage Stage-type variable for
     */
    public SceneSwitcherUi(Stage primStage) {
        this.primaryStage = primStage;
        loginViewUi = new LoginViewUi(primaryStage);
        tableViewUi = new TableViewUi(primaryStage);
    }

    /**
     * Constructor for ui scene switcher class
     */
    public SceneSwitcherUi() {

    }

    /**
     * Method to switch to login screen
     */
    public Scene switchToLogin(String stageTitle) {
        return loginViewUi.buildScene(stageTitle);
    }

    /**
     * Method to switch to the listing tableview
     */
    public Scene switchToTableView(String stageTitle) {
        return tableViewUi.buildScene(stageTitle);
    }

}
