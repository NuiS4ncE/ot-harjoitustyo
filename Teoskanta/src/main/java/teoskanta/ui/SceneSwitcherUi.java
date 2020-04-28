package teoskanta.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/*
* Class to switch different scenes
*/
public class SceneSwitcherUi {

    private Stage primaryStage;
    private LoginViewUi loginViewUi;
    private MainViewUi mainViewUi;
    private TableViewUi tableViewUi;

    /*
    * Constructor for ui scene switcher class 
    * @param primStage Stage-type variable for 
     */
    public SceneSwitcherUi(Stage primStage) {
        this.primaryStage = primStage;
        loginViewUi = new LoginViewUi(primaryStage);
        mainViewUi = new MainViewUi(primaryStage);
        tableViewUi = new TableViewUi(primaryStage);
    }
    
    /*
    * Constructor for ui scene switcher class
    */
    public SceneSwitcherUi() {

    }

    /*
    * Method to switch to login screen 
     */
    public Scene switchToLogin() {
        return loginViewUi.buildScene();
    }

    /*
    * Method to switch to main screen
     */
    public Scene SwitchToMain() {
        return mainViewUi.buildScene();
    }

    /* 
   * Method to switch to the listing tableview
     */
    public Scene switchToTableView() {
        return tableViewUi.buildScene();
    }

}
