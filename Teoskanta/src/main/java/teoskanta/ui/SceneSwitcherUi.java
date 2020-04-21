package teoskanta.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcherUi {

    private Stage primaryStage;
    private LoginViewUi loginViewUi;
    private MainViewUi mainViewUi;
    private TableViewUi tableViewUi;
    
    public SceneSwitcherUi(Stage primStage) {
        this.primaryStage = primStage;
        loginViewUi = new LoginViewUi(primaryStage);
        mainViewUi = new MainViewUi(primaryStage);
        tableViewUi = new TableViewUi(primaryStage);
    }

    public SceneSwitcherUi() {

    }

    public Scene switchToLogin() {
        return loginViewUi.buildScene();
    }

   /* public Scene SwitchToMain() {
        return mainViewUi.buildScene();
    } */
    
    public Scene switchToTableView() {
        return tableViewUi.buildScene();
    }

}
