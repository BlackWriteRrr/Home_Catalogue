import GUI.LoginGUI.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;


public class StartProgram extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginWindow ent = new LoginWindow();
        ent.entrance(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
