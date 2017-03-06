package GUI.MainWindowGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {//extends Application {

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
         stage.setTitle("Home catalogue");
         stage.setScene(new Scene(root, 600, 400));
         stage.show();

    }


}
