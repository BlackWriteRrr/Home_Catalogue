package DataBase;
import GUI.MainWindowGUI.CatalogueInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CatalogueDB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void createDB() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Catalogue.s3db");
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'catalog' ('Name' text, 'Location' text ,'Extension' text, 'Size' text , 'Type' text);");
    }

    public static void writeDB(CatalogueInformation inf, String type) throws SQLException, ClassNotFoundException {
        createDB();
        statmt.execute("INSERT INTO 'catalog' ('Name', 'Location', 'Extension','Size','Type') VALUES ('"+
                inf.getName()+"', '"+inf.getLocation()+"', '"+inf.getExtension()+"', '"+inf.getSize()+"', '"+type+"'); ");
        conn.close();
        statmt.close();
    }

    public static ObservableList<CatalogueInformation> readDB(ObservableList<CatalogueInformation> data, String Type, boolean flag)
            throws ClassNotFoundException, SQLException {
        createDB();
        resSet = statmt.executeQuery("SELECT * FROM catalog");
        data = FXCollections.observableArrayList();

        while (resSet.next()) {
            String name = resSet.getString("Name");
            String location = resSet.getString("Location");
            String extension = resSet.getString("Extension");
            String size = resSet.getString("Size");
            String type = resSet.getString("Type");

            if(type.equals(Type) && flag)
                data.add(new CatalogueInformation(name,location,extension,size));
            if(name.equals(Type) && !flag)
                data.add(new CatalogueInformation(name,location,extension,size));
        }
        closeDB();
        return data;
    }

    public static boolean checkFile(String path) throws ClassNotFoundException, SQLException {
        createDB();
        resSet = statmt.executeQuery("SELECT * FROM catalog");
        while (resSet.next()) {
            String location = resSet.getString("Location");
            if(location.equals(path)) {
                closeDB();
                return false;
            }
        }
        closeDB();
        return true;
    }

    public static void deleteDB(String loc) throws ClassNotFoundException, SQLException {
        createDB();
        statmt.execute("DELETE FROM catalog WHERE Location=='"+loc+"'");
        closeDB();
    }

    public static void closeDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSet.close();
    }
}
