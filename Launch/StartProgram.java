package Launch;

import GUI.LoginGUI.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartProgram extends Application {
private static Stage primaryStage;
public static User user;

    @Override
    public void start(Stage Stage) {
        primaryStage = Stage;
        LoginWindow ent = new LoginWindow();
        ent.entrance(primaryStage);
        primaryStage.show();
    }

    public static Stage getStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
