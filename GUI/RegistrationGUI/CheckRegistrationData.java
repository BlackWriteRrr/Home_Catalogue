package GUI.RegistrationGUI;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CheckRegistrationData {
    public void checkAll(RegistrationData reg) {

        if (checkEmail(reg.getEmail()) && checkPassword(reg)) {
            System.out.println("OK");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            //  alert.setHeaderText(null);
            alert.setContentText("Check your input data");
            alert.showAndWait();
        }

    }

    private boolean checkEmail(TextField text) {
        if (text.getText().isEmpty()) {
            return false;
        } else return true;

    }

    private boolean checkPassword(RegistrationData reg) {
        if (reg.getFirstPassword().getText().isEmpty() || !(reg.getFirstPassword().getText().equals(reg.getSecondPassword().getText()))) {
            return false;
        } else return true;

    }
}
