package GUI.RegistrationGUI;

import GUI.LoginGUI.LoginWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import static DataBase.RegistrationDB.checkLogin;
import static DataBase.RegistrationDB.writeDB;

public class CheckRegistrationData {
    public void checkAll(RegistrationData reg, Stage primaryStage) throws SQLException, ClassNotFoundException {

        if (checkEmail(reg.getEmail()) && checkPassword(reg)) {
            java.util.Date dateNow = new java.util.Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
            String currentData = formatForDateNow.format(dateNow);
            writeDB(reg,currentData);
            LoginWindow log = new LoginWindow();
            log.entrance(primaryStage);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Check your input data");
            alert.showAndWait();
        }

    }

    private boolean checkEmail(TextField text) {
        try {
            if (text.getText().isEmpty() || !checkLogin(text.getText()))
                return false;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
          return true;
    }

    private boolean checkPassword(RegistrationData reg) {
        if (reg.getFirstPassword().getText().isEmpty() || !(reg.getFirstPassword().getText().equals(reg.getSecondPassword().getText()))) {
            return false;
        } else return true;

    }
}
