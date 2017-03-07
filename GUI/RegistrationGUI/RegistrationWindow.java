package GUI.RegistrationGUI;


import GUI.LoginGUI.LoginWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Launch.StartProgram;

import java.sql.SQLException;


public class RegistrationWindow {
    private RegistrationData reg = new RegistrationData();
    private GridPane grid;
    private Button back;
    private Button regB;
    private Scene scene;

    public void registraation(Stage primaryStage) {
        primaryStage.close();
        primaryStage.setTitle("Registration");
        setGrid();
        setEmail();
        setPassword();
        setButtonBack();
        setButtonRegB();
        scene = new Scene(grid, 400, 350);
        scene.getStylesheets().add(LoginWindow.class.getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public Scene getScene(){
        return scene;
    }

    public void setGrid() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    public void setEmail() {
        reg.setEmail(new TextField());
        reg.getEmail().setPromptText("Email");
        GridPane.setColumnSpan(reg.getEmail(), 2);
        grid.add(reg.getEmail(), 0, 1);
    }

    public void setPassword() {
        reg.setFirstPassword(new PasswordField());
        reg.getFirstPassword().setPromptText("Password");
        GridPane.setColumnSpan(reg.getFirstPassword(), 2);
        grid.add(reg.getFirstPassword(), 0, 2);


        reg.setSecondPassword(new PasswordField());
        reg.getSecondPassword().setPromptText("Re-enter password ");
        GridPane.setColumnSpan(reg.getSecondPassword(), 2);
        grid.add(reg.getSecondPassword(), 0, 3);
    }

    public void setButtonBack() {
        back = new Button("Back");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(back);
        grid.add(hbBtn, 0, 4);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                LoginWindow log = new LoginWindow();
                log.entrance(StartProgram.getStage());
            }
        });
    }

    public void setButtonRegB() {
        regB = new Button("Registration");
        HBox hbBtnReg = new HBox(10);
        hbBtnReg.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnReg.getChildren().add(regB);
        grid.add(hbBtnReg, 1, 4);

        regB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                CheckRegistrationData check = new CheckRegistrationData();
                try {
                    check.checkAll(reg, StartProgram.getStage());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
