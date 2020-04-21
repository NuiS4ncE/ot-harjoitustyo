package teoskanta.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcherUi {

    private Stage primaryStage;
    private LoginViewUi loginViewUi;
    private MainViewUi mainViewUi;
    private CreateUserViewUi createUserViewUi;

    public SceneSwitcherUi(Stage primStage) {
        this.primaryStage = primStage;
        loginViewUi = new LoginViewUi(primaryStage);
        mainViewUi = new MainViewUi(primaryStage);
    }

    public SceneSwitcherUi() {

    }

    public Scene SwitchToLogin() {
        return loginViewUi.buildScene();
    }

    public Scene SwitchToMain() {
        return mainViewUi.buildScene();
    }

}
