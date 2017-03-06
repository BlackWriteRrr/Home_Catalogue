package GUI.MainWindowGUI;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import DataBase.CatalogueDB;
import javafx.stage.Stage;


public class MainWindowController {
    @FXML
    private TableView tv;
    @FXML
    private javafx.scene.control.TextField search;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn locationCol;
    @FXML
    private TableColumn extensionCol;
    @FXML
    private TableColumn sizeCol;
    private Stage stage;
    private Parent root;
    private ActionEvent actionEvent;
    private ObservableList<CatalogueInformation> data;

    @FXML
    public void AddInformation(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Open Document");//Заголовок диалога
        switch("" + choiceBox.getValue())
        {
            case "Video" :  FileChooser.ExtensionFilter extFilterVideo =
                    new FileChooser.ExtensionFilter("Video files (*.avi)", "*.avi");
                fileChooser.getExtensionFilters().add(extFilterVideo); break;

            case "Audio" :  FileChooser.ExtensionFilter extFilterAudio =
                    new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
                fileChooser.getExtensionFilters().add(extFilterAudio); break;

            case "Documents" :  FileChooser.ExtensionFilter extFilterDoc =
                    new FileChooser.ExtensionFilter("Doc files (*.docx)", "*.docx");
                fileChooser.getExtensionFilters().add(extFilterDoc); break;

            case "Images" :   FileChooser.ExtensionFilter extFilterImages =
                    new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                fileChooser.getExtensionFilters().add(extFilterImages); break;
        }

        File file = fileChooser.showOpenDialog(stage);//Указываем текущую сцену CodeNote.mainStage

        if (file != null) {
            double size = file.length();
            CatalogueInformation inf = new  CatalogueInformation(file.getName(), file.getPath(), getFileExtension(file) ,"" + size/1000 );
            CatalogueDB.writeDB(inf ,"" + choiceBox.getValue());
        }
        Update(actionEvent);
    }


    public void Update(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        data = CatalogueDB.readDB(data,"" + choiceBox.getValue(), true);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        extensionCol.setCellValueFactory(new PropertyValueFactory<>("extension"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        tv.setItems(data);

    }

    public void delete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int row = tv.getSelectionModel().getSelectedIndex();
        if(row==-1) return;
        String loc = String.valueOf(locationCol.getCellObservableValue(row).getValue());
        CatalogueDB.deleteDB(loc);
        Update(actionEvent);
    }

    public void search(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        data = CatalogueDB.readDB(data, search.getText(),false);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        extensionCol.setCellValueFactory(new PropertyValueFactory<>("extension"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        tv.setItems(data);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

    public void setDoubleMouseClick(MouseEvent mouseEvent) {
        tv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    int row = tv.getSelectionModel().getSelectedIndex();
                    if(row==-1) return;
                    String loc = String.valueOf(locationCol.getCellObservableValue(row).getValue());
                    openFile(loc);
                }
            }
        });
    }

    private  static void openFile(String path) {
        try {
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            desktop.open(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
