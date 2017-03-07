package GUI.LoginGUI;

import GUI.MainWindowGUI.MainWindowController;
import GUI.RegistrationGUI.RegistrationWindow;
import Launch.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Launch.StartProgram;

import java.sql.SQLException;

import static DataBase.RegistrationDB.checkSingIn;

public class LoginWindow {
    private GridPane grid;
    private Label email;
    private TextField userTextField;
    private Label pw;
    private PasswordField pwBox;
    private Button signIn;
    private Button signUp;
    private Button signInGuest;

    public void entrance (Stage primaryStage) {
        primaryStage.close();
        primaryStage.setTitle("Home cataloguer");
        setGrid();
        setEmailString();
        setPasswordString();
        setSignIn();
        setSignUp();
        setSignInGuest();
        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void setGrid() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
    }

    public void setEmailString() {
        email = new Label("Email:");
        grid.add(email, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 1, 1);
    }

    public void setPasswordString() {
        pw = new Label("Password:");
        grid.add(pw, 0, 2);

        pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
    }

    public void setSignIn() {
        signIn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(signIn);
        grid.add(hbBtn, 1, 4);

        signIn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    String password = getEncryptPassword(userTextField.getText(),pwBox.getText());
                    StartProgram.user = checkSingIn(userTextField.getText(), password);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                if(StartProgram.user!=null) {
                    try {
                        MainWindowController main = new MainWindowController();
                        main.start();
                    } catch (Exception e1) {
                        e1.getMessage();
                    }
                }
            }
        });
    }

    public void setSignInGuest(){
        signInGuest = new Button("Sign as Guest");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(signInGuest);
        grid.add(hbBtn, 0, 4);

        signInGuest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                MainWindowController main = new MainWindowController();
                StartProgram.user = new User("","Guest","",0);
                try {
                  main.start();
                }catch (Exception e1){
                    e1.getMessage();
                }
            }
        });
    }

    public void setSignUp() {

        signUp = new Button("Sign up");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(signUp);
        grid.add(hbBtn2, 1, 5);



        signUp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                RegistrationWindow reg = new RegistrationWindow();
                reg.registraation(StartProgram.getStage());
            }
        });
    }

    public String getEncryptPassword(String login, String pass){
        byte[] key = login.getBytes();
        byte[] arr = pass.getBytes();
        byte[] result = new byte[arr.length];
        for(int i = 0; i< arr.length; i++)
        {
            result[i] = (byte) (arr[i] ^ key[i % key.length]);
        }
        String encryptPassword = new String(result);
        return  encryptPassword;
    }
}
