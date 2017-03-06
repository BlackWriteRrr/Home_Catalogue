package GUI.RegistrationGUI;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationData {
    private TextField email;
    private PasswordField firstPassword;
    private PasswordField secondPassword;

    public void setEmail(TextField text) {
        email = text;
    }

    public TextField getEmail() {
        return email;
    }

    public void setFirstPassword(PasswordField text) {
        firstPassword = text;
    }

    public PasswordField getFirstPassword() {
        return firstPassword;
    }

    public void setSecondPassword(PasswordField text) {
        secondPassword = text;
    }

    public PasswordField getSecondPassword() {
        return secondPassword;
    }
}
