package GUI.RegistrationGUI;

import javafx.scene.Scene;
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

    public String getEncryptPassword(){
        byte[] arr = firstPassword.getText().getBytes();
        byte[] key = email.getText().getBytes();
        byte[] result = new byte[arr.length];
        for(int i = 0; i< arr.length; i++)
        {
            result[i] = (byte) (arr[i] ^ key[i % key.length]);
        }
        String encryptPassword = new String(result);
        return  encryptPassword;
    }
}
